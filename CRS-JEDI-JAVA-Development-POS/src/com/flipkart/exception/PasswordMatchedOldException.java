/**
 * 
 */
package com.flipkart.exception;

import com.flipkart.constant.SQLQueries;

/**
 * to be called during password change
 *
 */
public class PasswordMatchedOldException extends Exception{
	/**
	 * Constructor
	 * @param password -> password
	 */
	public PasswordMatchedOldException(String password) {
	}

	/**
	 * @return Message to be displayed
	 */
	@Override
	public String getMessage() 
	{
		return SQLQueries.ANSI_YELLOW +  "Entered password is same as old password" + SQLQueries.ANSI_RESET;
	}


}
