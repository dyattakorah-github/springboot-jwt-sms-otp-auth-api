package com.example.SpringAuth2.service;

import com.africastalking.AfricasTalking;
import com.africastalking.SmsService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AfricasTalkingSmsService implements NotificationService {
    @Value("${africastalking.sender-id}")
    private String senderId;

    public void sendMessage(String recipient, String message) {
        try {
            SmsService smsService = AfricasTalking.getService(AfricasTalking.SERVICE_SMS);

            smsService.send(
                    message,
                    senderId,
                    new String[]{recipient},
                    false
            );
        }
        catch (Exception e){
            throw new RuntimeException("Failed to send SMS", e);
        }
    }
}
