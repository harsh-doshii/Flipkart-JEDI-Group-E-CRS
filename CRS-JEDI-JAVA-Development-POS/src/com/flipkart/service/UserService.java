package com.flipkart.service;

import com.flipkart.bean.User;

public interface UserService {
    public void login(String username, String Password);
    public void setPassword(String username, String newPassword);
    public User getUser(String username);
}
