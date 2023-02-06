/**
 * 
 */
package com.flipkart.exception;

/**
 * 
 *
 */
public class RegistrationNotCompleteException extends Exception{
	private String studentId;

	/**
	 * @param studentId -> ID of student
	 */
	public RegistrationNotCompleteException(String studentId) {
		this.studentId = studentId;
	}
	
	/**
	 * @return the name
	 */
	public String getId() {
		return studentId;
	}
	
	/**
     * Overrides the error message shown for exception class
     * @return
     */
	@Override
	public String getMessage() {
		return "Student with id " + studentId+" is not registered"; 
	}
	
}