package com.example.SpringAuth2.dto;

public record RegisterRequest(
        String fullName,
        String phoneNumber,
        String password
) { }
