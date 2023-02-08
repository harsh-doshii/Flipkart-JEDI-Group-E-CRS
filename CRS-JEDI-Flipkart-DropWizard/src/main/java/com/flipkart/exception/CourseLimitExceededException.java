package com.flipkart.exception;

import com.flipkart.constant.SQLQueries;

/**
 * Exception for when registered courses for a student becomes > 4
 * 
 *
 */
public class CourseLimitExceededException extends Exception {

	private int num;
	
	/**
	 * Constructor
	 * @param num Number of courses
 	 */
	public CourseLimitExceededException(int num) {
		this.num = num;
	}

	/**
	 * Message returned when exception is thrown
	 */
	@Override
	public String getMessage() {
		return SQLQueries.ANSI_YELLOW + "Cannot register for more courses, already registered for " + num + " courses" + SQLQueries.ANSI_RESET;
	}
}