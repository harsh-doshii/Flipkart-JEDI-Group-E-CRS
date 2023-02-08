/**
 * 
 */
package com.flipkart.exception;

import com.flipkart.constant.SQLQueries;

/**
 *
 *
 */
public class PaymentAlreadyDoneException extends Exception{
	private int studentId;

	/**
	 * @param studentId -> ID of student
	 */
	public PaymentAlreadyDoneException(int studentId) {
		this.studentId = studentId;
	}
	
	/**
	 * @return the name
	 */
	public int getId() {
		return studentId;
	}
	
	/**
     * Overrides the error message shown for exception class
     * @return
     */
	@Override
	public String getMessage() {
		return SQLQueries.ANSI_YELLOW +  "Student with id " + studentId+" has already deposited fees" + SQLQueries.ANSI_RESET;
	}
}
