package com.flipkart.service;

import com.flipkart.bean.User;

public class UserServiceOperation implements UserService{
    @Override
    public boolean login(String username, String Password) {
        System.out.println("login successful");
        return true;
    }

    @Override
    public boolean setPassword(String username, String newPassword) {
        System.out.println("password set");
        return true;
    }

    @Override
    public User getUser(String username) {
        return null;
    }

    @Override
    public boolean updatePassword(String userid, String newPassword) {
        System.out.println("password updated");
        return true;
    }
}
