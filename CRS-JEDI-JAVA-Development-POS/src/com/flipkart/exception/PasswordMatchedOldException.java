/**
 * 
 */
package com.flipkart.exception;

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
		return "Entered password is same as old password";
	}


}
