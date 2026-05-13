package com.example.SpringAuth2.conttoller;

import com.example.SpringAuth2.dto.*;
import com.example.SpringAuth2.service.AuthenticationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequestMapping("/api/auth")
@RestController
@AllArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@RequestBody RegisterRequest registerRequest) {
        RegisterResponse response = authenticationService.registerUser(registerRequest);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/verify")
    public ResponseEntity<VerifyOtpResponse> verify(@RequestBody VerifyOtpRequest verifyOtpRequest) {
        VerifyOtpResponse response = authenticationService.verifyOtp(verifyOtpRequest);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        LoginResponse response = authenticationService.authenticateUser(loginRequest);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/resend-otp")
    public ResponseEntity<ResendOtpResponse> resendOtp(@RequestBody ResendOtpRequest req) {
        ResendOtpResponse response = authenticationService.resendOtp(req);
        return ResponseEntity.ok(response);
    }
}
