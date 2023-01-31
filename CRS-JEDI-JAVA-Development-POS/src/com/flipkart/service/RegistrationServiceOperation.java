package com.flipkart.service;

import com.flipkart.bean.RegisteredCourse;

import java.util.HashMap;
import java.util.List;

public class RegistrationServiceOperation implements RegistrationService{

    @Override
    public boolean registerCourse(String studentID, HashMap<Integer, Boolean> courseIDs) {
        return false;
    }

    @Override
    public boolean isRegistrationDone(String studentID) {
        return false;
    }

    @Override
    public boolean isPaymentDone(String studentID) {
        return false;
    }

    @Override
    public boolean addCourse(String studentID, int courseID) {
        return false;
    }

    @Override
    public boolean dropCourse(String studentID, int courseID) {
        return false;
    }

    @Override
    public List<RegisteredCourse> viewRegisteredCourse(String studentID) {
        return null;
    }

    @Override
    public float calculateFee(String studentID) {
        return 0;
    }

    @Override
    public void payFee(String studentID, float amount) {

    }
}
