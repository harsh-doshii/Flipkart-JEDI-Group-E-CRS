package com.flipkart.dao;

import com.flipkart.constant.SQLQueries;

import java.sql.*;
import java.text.SimpleDateFormat;

public class PaymentNotificationDAOImpl implements PaymentNotificationDAO {

    Connection conn = null;
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/crs_db";
    static final String USER = "root";
    static final String PASS = "Root@123";
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
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (Exception e) {
                throw new SQLException();
            }

            conn = DriverManager.getConnection(DB_URL,USER,PASS);

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
