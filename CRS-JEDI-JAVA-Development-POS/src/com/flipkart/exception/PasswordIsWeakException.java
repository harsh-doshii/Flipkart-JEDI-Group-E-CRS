/**
 * 
 */
package com.flipkart.exception;

import com.flipkart.constant.SQLQueries;

/**
 *
 *
 */
public class PasswordIsWeakException extends Exception {
	/**
	 * @param password -> password
	 */
	public PasswordIsWeakException(String password) {
	}

	/**
	 * @return message to be displayed
	 */
	@Override
	public String getMessage() 
	{
		return SQLQueries.ANSI_YELLOW + "Entered password has less than 4 characters" + SQLQueries.ANSI_RESET;
	}
}
