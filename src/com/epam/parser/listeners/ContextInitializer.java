package com.epam.parser.listeners;

import javax.servlet.ServletContextEvent;

import com.epam.parser.util.ResourceManager;



public class ContextInitializer implements javax.servlet.ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		ResourceManager.setRealPath(arg0.getServletContext().getRealPath("")
				+ System.getProperty("file.separator"));

	}

}
