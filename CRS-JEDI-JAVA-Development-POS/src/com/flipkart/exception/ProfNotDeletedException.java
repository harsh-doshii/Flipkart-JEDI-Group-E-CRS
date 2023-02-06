/**
 * 
 */
package com.flipkart.exception;

/**
 * am
 *
 */
public class ProfNotDeletedException extends Exception {
	private String ProfID;

    /**
     * Constructor for ProfNotAddedException
     * @param ProfID --> This is the ID of the prof being added
     */
    public ProfNotDeletedException(String ProfID) {
        this.ProfID = ProfID;
    }

    /**
     * getter method for ProfID
     * @return
     */
    public String getProfID() {
        return ProfID;
    }

    /**
     * overriden getMessage from Exception Class
     * @return
     */
    @Override
    public String getMessage() {
        return "Prof : " + ProfID + " cannot be deleted from the database because he/she is assigned to a course";
    }
}
