package com.flipkart.dao;

import com.flipkart.exception.*;

import java.sql.SQLException;

public interface UserDAO {

    /**
     * @param userId
     * @param pass
     * @return
     * @throws SQLException
     */
    public String login(int userId, String pass) throws PasswordMismatchException, UserNotFoundException;

    /**
     * @param userId
     * @param oldPass
     * @param newPass
     * @return
     * @throws SQLException
     */
    public boolean updatePassword(int userId, String oldPass, String newPass) throws PasswordMismatchException, UserNotFoundException;


    public  String getName(int userId) throws UserNotFoundException;
}
