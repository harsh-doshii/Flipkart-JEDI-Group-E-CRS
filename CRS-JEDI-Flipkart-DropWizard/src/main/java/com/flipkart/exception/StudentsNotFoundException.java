/**
 *
 */
package com.flipkart.exception;

import com.flipkart.constant.SQLQueries;

/**
 * to be called during login
 *
 */
public class StudentsNotFoundException extends Exception {

    public StudentsNotFoundException() {


    }
    /**
     * @return Message to be displayed
     */
    @Override
    public String getMessage()
    {
        return  SQLQueries.ANSI_YELLOW +  "No Students Found" + SQLQueries.ANSI_RESET;
    }

}
