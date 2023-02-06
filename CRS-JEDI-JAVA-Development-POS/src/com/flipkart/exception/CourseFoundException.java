package com.flipkart.exception;

/**
 * Exception class for when adding a course which already exists in the course catalogue
 */
public class CourseFoundException extends Exception{
    private int courseCode;

    /**
     * constructor for the exception class
     * @param courseCode --> this is the ID of the course which is being added
     */
    public CourseFoundException (int courseCode) {
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
        return "Course : " + courseCode + " already exists in the catalogue";
    }
}
