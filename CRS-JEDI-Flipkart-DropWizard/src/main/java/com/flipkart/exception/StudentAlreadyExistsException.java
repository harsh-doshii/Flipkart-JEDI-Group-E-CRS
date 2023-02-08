/**
 * 
 */
package com.flipkart.exception;

import com.flipkart.constant.SQLQueries;

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
		return SQLQueries.ANSI_YELLOW + "User with id: "+ this.studentID +" already exists!" + SQLQueries.ANSI_RESET ;
	}
}
