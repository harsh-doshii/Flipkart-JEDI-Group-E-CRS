/**
 * 
 */
package com.flipkart.exception;

import com.flipkart.constant.SQLQueries;

/**
 * am
 *
 */
public class UserNotAddedException extends Exception {

	private int userId;

	/**
	 * @param id -> ID of user
	 */
	public UserNotAddedException (int id) {
		setUserId(id);
	}
	
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	/**
	 * @return Message to be displayed
	 */
	public String getMessage() {

		return SQLQueries.ANSI_YELLOW +  "User with id: "+userId+" cannot be added" + SQLQueries.ANSI_RESET ;
	}
	
}
