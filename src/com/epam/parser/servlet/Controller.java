package com.epam.parser.servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;


import com.epam.parser.command.ICommand;
import com.epam.parser.command.util.CommandCreator;
import com.epam.parser.exception.CommandCreatingException;
import com.epam.parser.util.ResourceManager;
import com.epam.parser.util.StylesheetCache;

public class Controller extends HttpServlet {
	private static final Logger logger = Logger.getLogger(Controller.class);
			
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		ResourceManager.INSTANCE.loadResource("resources");
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException,
			java.net.MalformedURLException {
		processRequest(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException,
			java.net.MalformedURLException {
		processRequest(request, response);
	}

	private void processRequest(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		logger.info("request processing started");
		CommandCreator commandCreator = new CommandCreator();
		response.setContentType("text/html; charset=UTF-8");
		try {
			ICommand command = commandCreator.getCommand(request,response);
			command.execute(request,response);
			
		} catch (CommandCreatingException e) {
			logger.error("CommandCreatingException catched in "
					+ "Controller.processRequest, setting page = error_page", e);
		}
}
	
	public void destroy() {
		StylesheetCache.flushAll();
	}

	}
