package com.epam.parser.command.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;


import com.epam.parser.command.ICommand;
import com.epam.parser.command.NoCommand;
import com.epam.parser.exception.CommandCreatingException;

/**
 * This class call specific command class according to request parameter date:
 * January, 2013
 * 
 * @author Artsiom Stalpouski
 * @version 1.0
 */
public final class CommandCreator {
	private static Logger logger = Logger.getLogger(CommandCreator.class);
	
	/**
	 * Creates command according to request parameter "command" if there is no
	 * command, creator will return NoCommand class
	 * 
	 * @return command from enum
	 * @param wrapped request
	 * @see ICommand
	 * @throws CommandCreatingException when command name from request
	 * is not found in command enumeration
	 */
	public ICommand getCommand(HttpServletRequest request,
			HttpServletResponse response)
			throws CommandCreatingException {
		String commandName = request.getParameter("action");
		CommandEnum currentEnum;
		ICommand command;
		if (commandName == null) {
			command = new NoCommand();
		} else {

			try {
				currentEnum = CommandEnum.valueOf(commandName.toUpperCase());
				command = currentEnum.getCurrentCommand();
			} catch (IllegalArgumentException e) {
				logger.error("Exception caused while enum tried to parse command" +
						" parameter form request (probably illegal command in jsp code",e);
				throw new CommandCreatingException();
			}

		}
		return command;
	}
}
