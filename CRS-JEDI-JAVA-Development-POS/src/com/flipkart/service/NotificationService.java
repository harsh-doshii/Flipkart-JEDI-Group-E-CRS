package com.flipkart.service;

public interface NotificationService {
    /**
     * @param type
     * @param message
     * @param nid
     */
    public void sendNotification(String type, String message, String nid);
}
