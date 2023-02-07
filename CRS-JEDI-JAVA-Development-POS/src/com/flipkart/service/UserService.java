package com.flipkart.service;

import com.flipkart.bean.User;
import com.flipkart.exception.*;

public interface UserService {
    /**
     * @param userId
     * @param Password
     * @return role of the user who has logged in
     * @throws UserNotFoundException
     * @throws PasswordMismatchException
     */
    public String login(int userId, String Password) throws UserNotFoundException, PasswordMismatchException;

//    /**
//     * @param userId
//     * @param newPass
//     * @return true if successfully set password
//     * @throws UserNotFoundException
//     * @throws PasswordMatchedOldException
//     * @throws PasswordIsWeakException
//     */
//    public boolean setPassword(int userId, String oldPass, String newPass);

    /**
     * @param username
     * @return
     */
    public User getUser(String username);

    /**
     * @param userId
     * @param newPass
     * @return true if successfully set password
     * @throws UserNotFoundException
     * @throws PasswordMatchedOldException
     * @throws PasswordIsWeakException
     */
    public boolean updatePassword(int userId, String oldPass, String newPass) throws UserNotFoundException, PasswordMatchedOldException, PasswordIsWeakException;
}
