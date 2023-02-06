package com.flipkart.dao;

import java.sql.SQLException;

public interface UserDAO {

    public String login(int userId, String pass) throws SQLException;

    public boolean updatePassword(int userId, String oldPass, String newPass) throws SQLException;
}
