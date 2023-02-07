package com.flipkart.exception;

/**
 * Exception class for when removing a prof that does not exist in the database
 */
public class ProfNotFoundException extends Exception {
    private int ProfID;

    /**
     * Constructor for ProfFoundExceptionClass
     * @param ProfID --> This is the ID of the prof being removed
     */
    public ProfNotFoundException (int ProfID) {
        this.ProfID = ProfID;
    }

    /**
     * getter method for ProfID
     * @return
     */
    public int getProfID() {
        return ProfID;
    }

    /**
     * overriden getMessage from Exception Class
     * @return
     */
    @Override
    public String getMessage() {
        return "Prof : " + ProfID + " not found in the database";
    }
}
