package com.epam.parser.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *  This class is made for cases, when request with no "command" parameter 
 *  is send to servlet. It just gives main page path
 *  date: January, 2013
 * 
 * @see ICommand
 * @author Artsiom Stalpouski
 * @version 1.0
 */
public final class NoCommand implements ICommand {

	/**
	 * Always returns main page path every
	 * 
	 * @return main page path
	 * @param wrapped request
	 * @see ICommand
	 */
	@Override
	public void execute(HttpServletRequest request,
			HttpServletResponse response) {
		ShowPageCommand showPageCommand=new ShowPageCommand();
		showPageCommand.execute(request, response);
	}

	
}
