package com.flipkart.service;
import com.flipkart.bean.User;
import com.flipkart.dao.UserDAOImpl;
import com.flipkart.exception.*;

public class UserServiceOperation implements UserService{


    private static UserServiceOperation instance=null;

    private UserServiceOperation(){

    }

    public static UserServiceOperation getInstance() {
        if(instance ==null){
            instance = new UserServiceOperation();
        }
        return  instance;
    }
    @Override
    public String login(int userId, String Password) throws UserNotFoundException, PasswordMismatchException {
           return UserDAOImpl.getInstance().login(userId, Password);
    }

//    @Override
//    public boolean setPassword(int userId, String oldPass, String newPass) {
//
//            UserDAOImpl.getInstance().updatePassword(userId, oldPass, newPass);
//
//    }

    @Override
    public User getUser(String username) {
        return null;
    }

    @Override
    public boolean updatePassword(int userId, String oldPass, String newPass) throws  PasswordMatchedOldException {
        try {
            UserDAOImpl.getInstance().updatePassword(userId, oldPass, newPass);
            return true;
        }
        catch (Exception e){
           throw new PasswordMatchedOldException(newPass);
        }
    }
}
