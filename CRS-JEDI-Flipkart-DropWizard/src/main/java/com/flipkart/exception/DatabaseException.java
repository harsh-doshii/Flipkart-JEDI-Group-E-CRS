/**
 * 
 */
package com.flipkart.exception;

import com.flipkart.constant.SQLQueries;

/**
 * 
 *
 */
public class DatabaseException extends Exception {

	/**
	 * @return message of exception
	 */
	@Override
	public String getMessage() {
		return SQLQueries.ANSI_YELLOW + "Connection Error with the database. Try again." + SQLQueries.ANSI_RESET;
	}
	
	
}
