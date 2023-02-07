package com.flipkart.dao;

import java.sql.SQLException;

public interface PaymentNotificationDAO {

    /**
     * @param timestamp
     * @param studentId
     * @param amount
     * @return
     * @throws SQLException
     */
    public boolean addNotification(String timeStamp, int studentId, String message) throws SQLException;

}
