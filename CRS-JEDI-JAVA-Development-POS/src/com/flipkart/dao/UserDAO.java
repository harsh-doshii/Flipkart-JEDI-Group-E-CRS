package com.flipkart.dao;

import java.sql.SQLException;

public interface UserDAO {

    /**
     * @param userId
     * @param pass
     * @return
     * @throws SQLException
     */
    public String login(int userId, String pass) throws SQLException;

    /**
     * @param userId
     * @param oldPass
     * @param newPass
     * @return
     * @throws SQLException
     */
    public boolean updatePassword(int userId, String oldPass, String newPass) throws SQLException;
}
