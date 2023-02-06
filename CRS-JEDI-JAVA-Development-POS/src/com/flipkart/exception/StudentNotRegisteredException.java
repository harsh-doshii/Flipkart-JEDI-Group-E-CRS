/**
 * 
 */
package com.flipkart.exception;

/**
 * 
 *
 */
public class StudentNotRegisteredException extends Exception{
	private String name;

	/**
	 * @param studentName
	 */
	public StudentNotRegisteredException(String studentName) {
		this.name = studentName;
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
     * Overrides the error message shown for exception class
     * @return
     */
	@Override
	public String getMessage() {
		return "Student with name "+name+" is not registered"; 
	}
	
}
