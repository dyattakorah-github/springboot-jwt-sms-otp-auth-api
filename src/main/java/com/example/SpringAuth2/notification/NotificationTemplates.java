package com.example.SpringAuth2.notification;

public class NotificationTemplates {
    public static String verificationMessage(String otp) {
        return  "Your verification code is "  + otp;
    }
}
