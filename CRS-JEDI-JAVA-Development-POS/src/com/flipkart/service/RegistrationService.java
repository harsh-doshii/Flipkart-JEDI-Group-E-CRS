package com.flipkart.service;

import com.flipkart.bean.RegisteredCourse;

import java.util.HashMap;
import java.util.List;

public interface RegistrationService {

    public boolean registerCourse(String studentID, HashMap<Integer, Boolean> courseIDs);


    public boolean isRegistrationDone(String studentID);

    public boolean isPaymentDone(String studentID);

    public boolean addCourse(String studentID, int courseID);

    public boolean dropCourse(String studentID, int courseID);

    public List<RegisteredCourse> viewRegisteredCourse(String studentID);

    public float calculateFee(String studentID);

//    mode of payment left
    public void payFee(String studentID, float amount);
}
