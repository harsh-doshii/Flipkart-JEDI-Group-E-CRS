/**
 * 
 */
package com.flipkart.exception;

/**
 * to be called during login
 *
 */
public class StudentNotFoundException extends Exception {
	private int studentId;

	/**
	 * @param userId -> ID of the student
 	 */
	public StudentNotFoundException(int userId) {
		this.studentId = userId;
	}
	
	public int getUserId(){
		return this.studentId;
	}

	/**
	 * @return Message to be displayed
	 */
	@Override
	public String getMessage() 
	{
		return "User with userId " + this.studentId + " does not exist";
	}

}
