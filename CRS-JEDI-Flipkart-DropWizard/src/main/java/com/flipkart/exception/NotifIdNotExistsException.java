/**
 * 
 */
package com.flipkart.exception;

import com.flipkart.constant.SQLQueries;

/**
 *
 *
 */
public class NotifIdNotExistsException extends Exception{
	private int notifId;

    /**
     * Constructor
     * @param studentID -> ID of student whose grade is not assigned
     */
    public NotifIdNotExistsException(int notifId){
        this.notifId = notifId;
    }

    /**
     * Message of exception
     * @return -> exception's message
     */
    @Override
    public String getMessage() {
        return SQLQueries.ANSI_YELLOW +  "Notification with notification Id" + notifId + "does not exist" + SQLQueries.ANSI_RESET;
    }

    /**
     * Getter method for student ID.
     * @return -> StudentID
     */
    public int getnotifId(){
        return this.notifId;
    }
}
