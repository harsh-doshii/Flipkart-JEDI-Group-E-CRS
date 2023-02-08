package com.flipkart.service;

import com.flipkart.exception.PaymentAlreadyDoneException;

public interface PaymentService {
    /**
     * @param studentID
     * @return
     */
    public float calculateFee(int studentID);

    /**
     * @param studentID
     * @param amount
     */
    public void payFee(int studentID, float amount, String modeOfPayment) throws PaymentAlreadyDoneException;
}
