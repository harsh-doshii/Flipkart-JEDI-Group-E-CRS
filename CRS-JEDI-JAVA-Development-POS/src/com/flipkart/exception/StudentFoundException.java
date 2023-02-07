package com.flipkart.exception;
/**
 * Exception class for when adding a prof that already exists in the database
 */
public class StudentFoundException extends Exception {



        private String studentName;

        /**
         * Constructor for StudentFoundException
         * @param studentName --> This is the ID of the prof being added
         */
        public StudentFoundException (String studentName) {
            this.studentName = studentName;
        }

        /**
         * getter method for studentID
         * @return
         */
        public String getStudentName() {
            return studentName;
        }

        /**
         * overriden getMessage from Exception Class
         * @return
         */
        @Override
        public String getMessage() {
            return "Student : " + getStudentName() + " already exists in the database";
        }


}
