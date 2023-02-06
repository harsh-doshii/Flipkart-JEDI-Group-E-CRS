package com.flipkart.exception;

/**
 * Exception class for when a course cannot be removed from the course catalogue
 */
public class CourseNotDeletedException extends Exception{
    private int courseCode;

    /**
     * constructor for the exception class
     * @param courseCode --> this is the ID of the course which is cannot be removed
     */
    public CourseNotDeletedException (int courseCode) {
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
        return "Course : " + courseCode + " not deleted";
    }
}
