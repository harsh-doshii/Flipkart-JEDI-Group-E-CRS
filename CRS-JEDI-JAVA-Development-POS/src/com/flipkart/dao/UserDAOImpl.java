package com.flipkart.dao;

import java.sql.*;
import java.util.*;

import com.flipkart.bean.Course;
import com.flipkart.bean.Grade;
import com.flipkart.bean.RegisteredCourse;
import com.flipkart.bean.Student;
import java.sql.SQLException;
import com.flipkart.client.CRSApplication;
import com.flipkart.constant.SQLQueries;

public class UserDAOImpl implements UserDAO {
    static final String DB_URL = "jdbc:mysql://localhost/crs_db";
    private static UserDAOImpl instance = null;

    private PreparedStatement statement = null;
    //  Database credentials
    static final String USER = "root";
    static final String PASS = "Root@123";

    private UserDAOImpl() {

    }
    public static UserDAOImpl getInstance() {
        if (instance == null) {
            instance = new UserDAOImpl();
        }
        return instance;
    }


    /*
    *  Returns role of the user if user is authenticated.
    *
    *
    * */

    public String login(int userId, String pass) throws SQLException {
        //TODO:
        Connection connection = null;
        try {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (Exception e) {
                throw new SQLException();
            }

            connection = DriverManager.getConnection(DB_URL,USER,PASS);
            statement = connection.prepareStatement(SQLQueries.GET_USER_FROM_USER_ID);
            statement.setInt(1, userId);

            ResultSet rs = statement.executeQuery();

            if (!rs.next()) {
                System.out.println("USer with  " + userId + " NOT found");
                return "INVALID USER";
            }

            String password = rs.getString("password");

            if (pass.equals(password)) {
                int roleId = rs.getInt("roleid");
                statement = connection.prepareStatement(SQLQueries.GET_ROLE_FROM_ROLE_ID);
                statement.setInt(1, roleId);
                rs = statement.executeQuery();

                if (!rs.next()) {
                    throw new SQLException();
                    //return "INVALID USER";
                }

                String role = rs.getString("roleName");

                return role;

            } else {
                System.out.println("USer with  " + userId + " NOT found");
                return "INVALID USER";
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
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }


    public boolean updatePassword(int userId, String oldPass, String newPass) throws SQLException {
        Connection connection = null;
        try {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (Exception e) {
                throw new SQLException();
            }

            String role = login(userId, oldPass);

            if (role.equals("INVALID USER")) {
                System.out.println("Enter correct Password");
                return false;
            }

            connection = DriverManager.getConnection(DB_URL,USER,PASS);
            statement = connection.prepareStatement(SQLQueries.UPDATE_USER_PASSWORD);

            statement.setString(1, newPass);
            statement.setInt(2, userId);

            int row = statement.executeUpdate();

            if (row == 0) {
                System.out.println("Password not updated");
                return false;
            }

            System.out.println("Updated password successfully, Enjoy:)");
            return true;
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
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }


}
