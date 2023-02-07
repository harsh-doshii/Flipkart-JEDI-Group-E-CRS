/**
 * 
 */
package com.flipkart.exception;

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
		return "User with id: "+userId+" cannot be added";
	}
	
}
