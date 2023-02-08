package com.flipkart.service;

import com.flipkart.dao.PaymentNotificationDAOImpl;
import com.flipkart.dao.StudentDAOImpl;

import java.text.SimpleDateFormat;

public class NotificationServiceOperation implements NotificationService{

    private static NotificationServiceOperation instance=null;

    private NotificationServiceOperation(){

    }

    public static NotificationServiceOperation getInstance() {
        if(instance ==null){
            instance = new NotificationServiceOperation();
        }
        return  instance;
    }

    public void addNotification(int studentId, String message) {
        try {
            String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
            PaymentNotificationDAOImpl.getInstance().addNotification(timeStamp, studentId, message);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
