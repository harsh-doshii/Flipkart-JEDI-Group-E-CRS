package com.flipkart.exception;

/**
 * Exception for when all seats for a course are full
 * 
 *
 */

public class CourseSeatsFullException extends Exception {

    private int courseID;

    /**
     * Constructor
     * @param courseID
     */
    public CourseSeatsFullException(int courseID){
        this.courseID = courseID;
    }

    /**
	 * Message returned when exception is thrown
	 */
	@Override
	public String getMessage() {
		return  "Seats are not available in : " + courseID;
	}

}
