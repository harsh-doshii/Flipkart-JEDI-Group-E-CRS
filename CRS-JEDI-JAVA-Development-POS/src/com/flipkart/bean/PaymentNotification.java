package com.flipkart.bean;

public class PaymentNotification {
    private String timestamp;

    private String notification;

    public PaymentNotification (String timestamp, String notification) {
        this.timestamp = timestamp;
        this.notification = notification;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getNotification() {
        return notification;
    }

    public void setNotification(String notification) {
        this.notification = notification;
    }
}
