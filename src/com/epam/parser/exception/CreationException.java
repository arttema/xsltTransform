
package com.epam.parser.exception;

/**
 * Signals that creating of entity exception has occurred. This
 * class is the general class of exceptions produced by creating Java Bean object
 * by DAO operations.
 *	
 *	date: January, 2013
 * 
 * @author Artsiom Stalpouski
 * @version 1.0
 */
public class CreationException extends Exception {


	private static final long serialVersionUID = 1L;


	/**
	 * @param arg0
	 */
	public CreationException(String arg0) {
		super(arg0);
		
	}

	/**
	 * @param arg0
	 */
	public CreationException(Throwable arg0) {
		super(arg0);

	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public CreationException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		
	}
}
