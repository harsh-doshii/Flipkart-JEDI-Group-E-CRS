package com.flipkart.exception;

/**
 * Exception Class for when a prof cannot be added to the database
 */
public class ProfNotAddedException extends Exception {
    private String ProfID;

    /**
     * Constructor for ProfNotAddedException
     * @param ProfID --> This is the ID of the prof being added
     */
    public ProfNotAddedException(String ProfID) {
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
        return "Prof : " + ProfID + " cannot be added to the database";
    }
}
