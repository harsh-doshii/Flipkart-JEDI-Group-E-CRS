/**
 * 
 */
package com.flipkart.exception;

import com.flipkart.constant.SQLQueries;

/**
 * to be called during login
 *
 */
public class PasswordMismatchException extends Exception{

	/**
	 * Constructor
	 * @param password password
	 */
	public PasswordMismatchException(String password) {
	}

	/**
	 * @return Message to be displayed
	 */
	@Override
	public String getMessage() 
	{
		return SQLQueries.ANSI_YELLOW + "Entered incorrect password" + SQLQueries.ANSI_RESET;
	}

}
