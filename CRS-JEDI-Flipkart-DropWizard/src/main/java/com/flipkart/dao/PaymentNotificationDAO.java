package com.flipkart.dao;

import java.sql.SQLException;

public interface PaymentNotificationDAO {

    /**
     * @param timeStamp
     * @param studentId
     * @param message
     * @return
     * @throws SQLException
     */
    public boolean addNotification(String timeStamp, int studentId, String message) throws SQLException;

}
