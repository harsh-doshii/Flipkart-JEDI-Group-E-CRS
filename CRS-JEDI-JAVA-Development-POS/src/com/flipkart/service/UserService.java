package com.flipkart.service;

import com.flipkart.bean.User;

public interface UserService {
    public void login(String username, String Password);
    public void setPassword(String userid, String password);
    public User getUser(String username);

    public void updatePassword(String userid, String newPassword);
}
