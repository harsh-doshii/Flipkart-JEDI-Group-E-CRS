/**
 * 
 */
package com.flipkart.exception;

/**
 * am
 *
 */
public class StudentNotAddedException extends Exception {

	private String studentId;

	/**
	 * @param id -> ID of student
	 */
	public StudentNotAddedException(String id) {
		setUserId(id);
	}
	
	public String getUserId() {
		return this.studentId;
	}

	public void setUserId(String userId) {
		this.studentId = userId;
	}

	/**
	 * @return -> Message to be displayed
	 */
	public String getMessage() {
		return "User with id: "+ this.studentId +" cannot be added";
	}
	
}
