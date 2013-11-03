package com.epam.parser.command.util;

import org.apache.log4j.Logger;


import com.epam.parser.command.AddCommand;
import com.epam.parser.command.BackCommand;
import com.epam.parser.command.ICommand;
import com.epam.parser.command.SaveCommand;
import com.epam.parser.command.ShowPageCommand;
import com.epam.parser.exception.CommandCreatingException;

/**
 *  This class helps creating command. It is used to select right command from enum,
 *  which contains all existing logic command elements 
 *  
 *  date: January, 2013
 * 
 * @author Artsiom Stalpouski
 * @version 1.0
 */
public enum CommandEnum {

	
	SAVE {
		
		public ICommand getCurrentCommand() throws CommandCreatingException {
			logger.info("Save Command created");
			return new SaveCommand();
			
		}		
	},
	
	ADD {
		
		public ICommand getCurrentCommand() throws CommandCreatingException {
			logger.info("Add Command created");
			return new AddCommand();
			
		}		
	},
	SUBCATEGORIES {
		
		public ICommand getCurrentCommand() throws CommandCreatingException {
			logger.info("Subcategories(ShowPage) Command created");
			return new ShowPageCommand();
			
		}		
	},
	CATEGORIES {
		
		public ICommand getCurrentCommand() throws CommandCreatingException {
			logger.info("Categories(ShowPage) Command created");
			return new ShowPageCommand();
			
		}		
	},
	GOODS {
		
		public ICommand getCurrentCommand() throws CommandCreatingException {
			logger.info("Goods Command created");
			return new ShowPageCommand();
			
		}		
	},
	BACK {
		
		public ICommand getCurrentCommand() throws CommandCreatingException {
			logger.info("Back Command created");
			return new BackCommand();
			
		}		
	};
	private static Logger logger = Logger.getLogger(CommandEnum.class);

	public abstract ICommand getCurrentCommand() throws CommandCreatingException;
}
