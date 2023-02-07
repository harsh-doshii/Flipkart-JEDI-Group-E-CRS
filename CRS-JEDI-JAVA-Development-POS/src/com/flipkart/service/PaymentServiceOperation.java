package com.flipkart.service;

import com.flipkart.dao.PaymentDAOImpl;
import com.flipkart.dao.PaymentNotificationDAOImpl;

public class PaymentServiceOperation implements PaymentService{

    private static PaymentServiceOperation instance=null;

    private PaymentServiceOperation(){

    }

    public static PaymentServiceOperation getInstance() {
        if(instance ==null){
            instance = new PaymentServiceOperation();
        }
        return  instance;
    }

    @Override
    public float calculateFee(int studentID) {
        try {
            return PaymentDAOImpl.getInstance().calculateRemainingFee(studentID);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return -1;
        }
    }
    @Override
    public void payFee(int studentID, float amount) {
        try {
            int id = PaymentDAOImpl.getInstance().makePayment(studentID, amount);
            NotificationServiceOperation.getInstance().addNotification(studentID,"you have made the payment of amount " + amount + " your transaction id is " + id);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
