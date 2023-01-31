package com.flipkart.service;

import com.flipkart.bean.User;

public interface UserService {
    public boolean login(String username, String Password);
    public boolean setPassword(String userid, String password);
    public User getUser(String username);

    public boolean updatePassword(String userid, String newPassword);
}
