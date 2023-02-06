package com.flipkart.service;
import com.flipkart.bean.User;
import com.flipkart.dao.UserDAOImpl;

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
    public String login(int userId, String Password)  {
        try {
           return UserDAOImpl.getInstance().login(userId, Password);

        }
        catch (Exception e){
            return "INVALID USER";
        }
//       return
    }

    @Override
    public boolean setPassword(int userId, String oldPass, String newPass) {
        try {
            UserDAOImpl.getInstance().updatePassword(userId, oldPass, newPass);
            return true;
        }
        catch (Exception e){
            return  false;
        }
    }

    @Override
    public User getUser(String username) {
        return null;
    }

    @Override
    public boolean updatePassword(int userId, String oldPass, String newPass) {
        try {
            UserDAOImpl.getInstance().updatePassword(userId, oldPass, newPass);
            return true;
        }
        catch (Exception e){
            return  false;
        }
    }
}
