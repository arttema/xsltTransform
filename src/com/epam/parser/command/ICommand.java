package com.epam.parser.command;

import java.util.concurrent.locks.ReentrantReadWriteLock;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This class is common interface for all specific commands date: January, 2013
 * 
 * @see ICommand
 * @author Artsiom Stalpouski
 * @version 1.0
 */
public interface ICommand {
	static final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
	/**
	 * Common interface for all specific commands
	 * 
	 * @return page path
	 * @param wrapped request
	 * 
	 */
	public abstract void execute(HttpServletRequest request,
			HttpServletResponse response);
}
