/**
 * 
 */
package com.flipkart.exception;

import com.flipkart.constant.SQLQueries;

/**
 * am
 *
 */
public class ProfNotDeletedException extends Exception {
	private int ProfID;

    /**
     * Constructor for ProfNotAddedException
     * @param ProfID --> This is the ID of the prof being added
     */
    public ProfNotDeletedException(int ProfID) {
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
        return  SQLQueries.ANSI_YELLOW +  "Prof : " + ProfID + " cannot be deleted from the database because he/she is assigned to a course" + SQLQueries.ANSI_RESET;
    }
}
