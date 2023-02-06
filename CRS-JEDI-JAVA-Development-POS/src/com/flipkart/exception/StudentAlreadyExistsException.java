/**
 * 
 */
package com.flipkart.exception;

/**
 * am
 *
 */
public class StudentAlreadyExistsException extends Exception {

	private String studentID;

	/**
	 * @param id -> ID of student
	 */
	public StudentAlreadyExistsException(String id) {
		setUserId(id);
	}
	
	public String getUserId() {
		return this.studentID;
	}

	public void setUserId(String userId) {
		this.studentID = userId;
	}

	/**
	 * @return -> Message to be displayed
	 */
	public String getMessage() {
		return "User with id: "+ this.studentID +" already exists!";
	}
}
