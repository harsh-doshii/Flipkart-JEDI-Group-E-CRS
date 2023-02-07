package com.flipkart.service;

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
    public void payFee(int studentID, float amount);
}
