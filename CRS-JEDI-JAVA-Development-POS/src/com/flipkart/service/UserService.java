package com.flipkart.service;

import com.flipkart.bean.User;

public interface UserService {
    public String login(int userId, String Password);
    public boolean setPassword(int userId, String oldPass, String newPass);
    public User getUser(String username);

    public boolean updatePassword(int userId, String oldPass, String newPass);
}
