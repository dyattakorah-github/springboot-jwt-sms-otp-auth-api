package com.example.SpringAuth2.config;

import com.africastalking.AfricasTalking;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ATConfig {
    @Value("${africastalking.api-key}")
    private String apiKey;

    @Value("${africastalking.username}")
    private String username;

    @PostConstruct
    public void init() {
        AfricasTalking.initialize(username, apiKey);
    }
}
