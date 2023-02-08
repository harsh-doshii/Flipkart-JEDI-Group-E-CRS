package com.flipkart.dao;

import com.flipkart.exception.PaymentAlreadyDoneException;

import java.sql.SQLException;

public interface PaymentDAO {
    /**
     * @param studentID
     * @return
     * @throws SQLException
     */
    public float calculateRemainingFee(int studentID) throws SQLException;

    /**
     * @param studentId
     * @param amount
     * @return
     * @throws SQLException
     */
    public int makePayment(int studentId, float amount, String modeOfPayment) throws PaymentAlreadyDoneException;

}
