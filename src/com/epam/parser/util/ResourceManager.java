package com.epam.parser.util;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * This helper class is wrapper for recource bundle made to serve jsp page path
 * 
 * date: January, 2013
 * 
 * @author Artsiom Stalpouski
 * @version 1.2
 */
public enum ResourceManager {
	INSTANCE;
	public static final String FS = System.getProperty("file.separator");
	private static String realPath;
	private ResourceBundle resourceBundle;
	private final Locale defaultLocale = Locale.getDefault();

	private ResourceManager() {
		resourceBundle = ResourceBundle.getBundle("resources", defaultLocale);
	}

	/**
	 * Return property value
	 * 
	 * @param key
	 * @see ResourceBundle.getProperty()
	 */
	public String getProperty(String key) {
		return resourceBundle.getString(key);
	}

	/**
	 * Used to get file path properties with servlet context real path
	 * 
	 * @param key
	 * @see ResourceBundle.getProperty()
	 */
	public String getPropertyRealPath(String key) {
		return (realPath + resourceBundle.getString(key));
	}

	/**
	 * Changes properties file
	 * 
	 * @param filename
	 * @see ResourceBundle.getBundle()
	 */
	public void loadResource(String resourceName) {

		resourceBundle = ResourceBundle.getBundle(resourceName, defaultLocale);
	}

	public static String getRealPath() {
		return realPath;
	}

	public static void setRealPath(String realPath) {
		ResourceManager.realPath = realPath;
	}

}
