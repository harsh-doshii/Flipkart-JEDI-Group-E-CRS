/**
 * 
 */
package com.flipkart.exception;

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
		return "Entered password has less than 4 characters";
	}
}
