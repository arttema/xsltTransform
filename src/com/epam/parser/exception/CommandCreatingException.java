
package com.epam.parser.exception;

/**
 * Signals that an Command creating exception of some sort has occurred. This
 * class is the general class of exceptions produced by failed or
 * interrupted command creating operations.
 *	
 *	date: January, 2013
 * 
 * @author Artsiom Stalpouski
 * @version 1.0
 */
public class CommandCreatingException extends Exception {


	private static final long serialVersionUID = 1L;


	public CommandCreatingException() {
	}

	/**
	 * @param arg0
	 */
	public CommandCreatingException(String arg0) {
		super(arg0);
	}

	/**
	 * @param arg0
	 */
	public CommandCreatingException(Throwable arg0) {
		super(arg0);
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public CommandCreatingException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}
}
