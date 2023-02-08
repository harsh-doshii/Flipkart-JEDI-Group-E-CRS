package com.flipkart.exception;

import com.flipkart.constant.SQLQueries;

/**
 * Exception class for when grade is not assigned to student.
 */
public class GradeNotAssignedException extends Exception{
    private int studentID;

    /**
     * Constructor
     * @param studentID -> ID of student whose grade is not assigned
     */
    public GradeNotAssignedException(int studentID){
        this.studentID = studentID;
    }

    /**
     * Message of exception
     * @return -> exception's message
     */
    @Override
    public String getMessage() {
        return SQLQueries.ANSI_YELLOW + "Grade not assigned to student with ID " + studentID + SQLQueries.ANSI_RESET;
    }

    /**
     * Getter method for student ID.
     * @return -> StudentID
     */
    public int getStudentID(){
        return this.studentID;
    }

}
