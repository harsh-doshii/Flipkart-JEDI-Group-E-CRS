/**
 * 
 */
package com.flipkart.exception;

/**
 * to be called during login
 *
 */
public class UserNotFoundException extends Exception {
	private String userId;

	/**
	 * @param userId -> ID of the user
	 */
	public UserNotFoundException(String userId) {
		this.userId = userId;
	}
	
	public String getUserId(){
		return userId;
	}

	/**
	 * @return Message to be displayed
	 */
	@Override
	public String getMessage() 
	{
		return "User with userId " + userId + " does not exist";
	}

}
