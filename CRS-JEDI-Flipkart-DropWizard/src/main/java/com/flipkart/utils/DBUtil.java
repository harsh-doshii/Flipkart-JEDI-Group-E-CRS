/**
 *
 */
package com.flipkart.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {

    /**
     * Get connection of SQL Database
     * @return -> Connection
     */
    @SuppressWarnings("unused")
    public static Connection getConnection() {

        Connection connection = null;

        if (connection != null) {
            return connection;
        }
        else
        {
            try
            {
                String driver = "com.mysql.cj.jdbc.Driver";
                String url = "jdbc:mysql://localhost/crs_db";
                String user = "root";
                String password = "Root@123";
                Class.forName(driver);
                connection = DriverManager.getConnection(url, user, password);
            }
            catch (ClassNotFoundException e){
                System.out.println(e.getMessage());
            }
            catch (SQLException e) {
                System.out.println(e.getMessage());

            }
            return connection;
        }

    }
}