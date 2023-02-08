package com.flipkart.exception;

import com.flipkart.constant.SQLQueries;

/**
 * Exception class for when course cannot be removed because it doesn't exist in the course catalogue
 */
public class CourseNotFoundException extends Exception{
    private int courseCode;

    /**
     * constructor for the exception class
     * @param courseCode --> this is the ID of the course which is cannot be found
     */
    public CourseNotFoundException(int courseCode) {
        this.courseCode = courseCode;
    }

    /**
     * getter method for @param courseCode
     * @return
     */
    public int getCourseCode() {
        return courseCode;
    }

    /**
     * Overrides the error message shown for exception class
     * @return
     */
    @Override
    public String getMessage() {
        return SQLQueries.ANSI_YELLOW  + "Course : " + courseCode + " not found in the course catalogue" + SQLQueries.ANSI_RESET;
    }
}
