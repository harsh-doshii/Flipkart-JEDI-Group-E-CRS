/**
 * 
 */
package com.flipkart.exception;

import com.flipkart.constant.SQLQueries;

/**
 * to be called during login
 *
 */
public class UserNotFoundException extends Exception {
	private int userId;

	/**
	 * @param userId -> ID of the user
	 */
	public UserNotFoundException(int userId) {
		this.userId = userId;
	}
	
	public int getUserId(){
		return userId;
	}

	/**
	 * @return Message to be displayed
	 */
	@Override
	public String getMessage() 
	{

		return SQLQueries.ANSI_YELLOW +   "User with userId " + userId + " does not exist" + SQLQueries.ANSI_RESET ;
	}

}
