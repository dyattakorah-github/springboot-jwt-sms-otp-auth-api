package com.example.SpringAuth2.dto;

public record VerifyOtpRequest(String phoneNumber, String verificationCode) {
}
