package com.flipkart.service;

import com.flipkart.bean.User;

public interface UserService {
    /**
     * @param userId
     * @param Password
     * @return
     */
    public String login(int userId, String Password);

    /**
     * @param userId
     * @param oldPass
     * @param newPass
     * @return
     */
    public boolean setPassword(int userId, String oldPass, String newPass);

    /**
     * @param username
     * @return
     */
    public User getUser(String username);

    /**
     * @param userId
     * @param oldPass
     * @param newPass
     * @return
     */
    public boolean updatePassword(int userId, String oldPass, String newPass);
}
