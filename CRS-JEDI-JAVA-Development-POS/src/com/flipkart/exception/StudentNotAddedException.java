/**
 * 
 */
package com.flipkart.exception;

import com.flipkart.constant.SQLQueries;

/**
 * am
 *
 */
public class StudentNotAddedException extends Exception {

	private String studentName;

	/**
	 * @param name -> ID of student
	 */
	public StudentNotAddedException(String name) {
		setUserName(name);
	}
	
	public String getUserName() {
		return this.studentName;
	}

	public void setUserName(String userName) {
		this.studentName = studentName;
	}

	/**
	 * @return -> Message to be displayed
	 */
	public String getMessage() {
		return  SQLQueries.ANSI_YELLOW +  "User with name: "+ getUserName() +" cannot be added" + SQLQueries.ANSI_RESET;
	}
	
}
