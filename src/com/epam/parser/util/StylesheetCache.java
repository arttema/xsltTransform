package com.epam.parser.util;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import javax.xml.transform.Source;
import javax.xml.transform.Templates;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamSource;

/**
 * A utility class that caches XSLT stylesheets in memory.
 * 
 */
public final class StylesheetCache {
	private static final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
	// map xslt file names to MapEntry instances
	// (MapEntry is defined below)
	private static Map cache = new HashMap();

	/**
	 * Flush all cached stylesheets from memory, emptying the cache.
	 */
	public static void flushAll() {
		lock.readLock().lock();
		try {
			cache.clear();
		} finally {
			lock.readLock().unlock();
		}
	}

	/**
	 * Flush a specific cached stylesheet from memory.
	 * 
	 * @param xsltFileName
	 *            the file name of the stylesheet to remove.
	 */
	public static void flush(String xsltFileName) {
		lock.readLock().lock();
		try {
			cache.remove(xsltFileName);
		} finally {
			lock.readLock().unlock();
		}
	}

	/**
	 * Obtain a new Transformer instance for the specified XSLT file name. A new
	 * entry will be added to the cache if this is the first request for the
	 * specified file name.
	 * 
	 * @param xsltFileName
	 *            the file name of an XSLT stylesheet.
	 * @return a transformation context for the given stylesheet.
	 */
	public static Transformer newTransformer(String xsltFileName)
			throws TransformerConfigurationException {
		File xsltFile = new File(xsltFileName);

		// determine when the file was last modified on disk
		long xslLastModified;
		MapEntry entry;

		lock.readLock().lock();
		try {
			xslLastModified = xsltFile.lastModified();
			entry = (MapEntry) cache.get(xsltFileName);
		} finally {
			lock.readLock().unlock();
		}
		if (entry != null) {
			// if the file has been modified more recently than the
			// cached stylesheet, remove the entry reference
			if (xslLastModified > entry.lastModified) {
				entry = null;
			}
		}

		// create a new entry in the cache if necessary
		if (entry == null) {
			lock.writeLock().lock();
			try {
				if (entry == null) {
					Source xslSource = new StreamSource(xsltFile);
					TransformerFactory transFact = TransformerFactory
							.newInstance();
					Templates templates = transFact.newTemplates(xslSource);
					entry = new MapEntry(xslLastModified, templates);
					cache.put(xsltFileName, entry);
				}
			} finally {
				lock.writeLock().unlock();
			}
		}
		return entry.templates.newTransformer();
	}

	// prevent instantiation of this class
	private StylesheetCache() {
	}

	/**
	 * This class represents a value in the cache Map.
	 */
	static final class MapEntry {
		long lastModified; // when the file was modified
		Templates templates;

		MapEntry(long lastModified, Templates templates) {
			this.lastModified = lastModified;
			this.templates = templates;
		}
	}
}