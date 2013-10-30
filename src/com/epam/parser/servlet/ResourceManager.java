package com.epam.parser.servlet;


import java.util.Locale;
import java.util.ResourceBundle;
/**
 *  This helper class is wrapper for recource bundle made to serve jsp page path
 *  
 *  date: January, 2013
 * 
 * @author Artsiom Stalpouski
 * @version 1.0
 */
public enum ResourceManager {
	INSTANCE;
	public final static String FS = System.getProperty("file.separator");
	private ResourceBundle resourceBundle;
	private final Locale defaultLocale = Locale.getDefault();
	private static String resourceName;
	
	private ResourceManager() {
	}
	/**
	 * Return property value
	 * @param key
	 * @see ResourceBundle.getProperty()
	 */
	public String getProperty(String key) {
		return resourceBundle.getString(key);
	}
	
	public void loadResource(){
		resourceBundle = ResourceBundle.getBundle(resourceName, defaultLocale);
	}
	
	/**
	 * Changes properties file
	 * @param filename
	 * @see ResourceBundle.getBundle()
	 */
	public void setResourceName(String resourceName) {
		ResourceManager.resourceName=resourceName;
	}
	
}

