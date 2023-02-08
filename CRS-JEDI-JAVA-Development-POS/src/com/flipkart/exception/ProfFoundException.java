package com.flipkart.exception;

import com.flipkart.constant.SQLQueries;

/**
 * Exception class for when adding a prof that already exists in the database
 */
public class ProfFoundException extends Exception {
    private int ProfID;

    /**
     * Constructor for ProfFoundExceptionClass
     * @param ProfID --> This is the ID of the prof being added
     */
    public ProfFoundException (int ProfID) {
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
        return  SQLQueries.ANSI_YELLOW +  "Prof : " + ProfID + " already exists in the database" + SQLQueries.ANSI_RESET;
    }
}
