package com.flipkart.dao;

import com.flipkart.constant.SQLQueries;

import java.sql.*;
import java.text.SimpleDateFormat;
import com.flipkart.utils.DBUtil;


public class PaymentNotificationDAOImpl implements PaymentNotificationDAO {

    Connection conn = null;

    PreparedStatement statement = null;

    private static volatile PaymentNotificationDAOImpl instance = null;

    private PaymentNotificationDAOImpl(){}

    public static PaymentNotificationDAOImpl getInstance() {
        if (instance == null) {
            synchronized (PaymentNotificationDAOImpl.class) {
                instance = new PaymentNotificationDAOImpl();
            }
        }
        return instance;
    }

    @Override
    public boolean addNotification(String timeStamp, int studentId, String message) throws SQLException {

        try {
            conn = DBUtil.getConnection();

            statement = conn.prepareStatement(SQLQueries.ADD_NOTIFICATION);
            statement.setString(1,timeStamp);
            statement.setInt(2, studentId);
            statement.setString(3, message);
            int row = statement.executeUpdate();
            if (row == 0) {
                System.out.println("notification not added, try again later!");
                return false;
            }
            else {
//                System.out.println("payment notification added");
                return true;
            }
        } catch (SQLException se) {
            se.printStackTrace();
            throw new SQLException();
        } finally {
            //finally block used to close resources
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException se2) {
                throw new SQLException();
            }// nothing we can do
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        
//        return false;
    }
}
