package com.flipkart.exception;

/**
 * Exception Class for when a prof cannot be added to the database
 */
public class ProfNotAddedException extends Exception {
    private String profName;

    /**
     * Constructor for ProfNotAddedException
     */
    public ProfNotAddedException(String name) {
        this.profName = name;
    }

    /**
     * getter method for ProfID
     * @return
     */
    public String getProfName() {
        return profName;
    }

    /**
     * overriden getMessage from Exception Class
     * @return
     */
    @Override
    public String getMessage() {
        return "Prof : " + profName + " cannot be added to the database";
    }
}
