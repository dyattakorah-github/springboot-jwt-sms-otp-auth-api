package com.example.SpringAuth2.service;

import com.example.SpringAuth2.dto.*;
import com.example.SpringAuth2.model.User;
import com.example.SpringAuth2.notification.NotificationTemplates;
import com.example.SpringAuth2.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;


@Service
@AllArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final NotificationService notificationService;

    private String generateVerificationCode() {
        SecureRandom random = new SecureRandom();
        int otp = random.nextInt(900000) + 100000;
        return String.valueOf(otp);
    }

    public RegisterResponse registerUser(RegisterRequest request) {
        if (userRepository.existsByPhoneNumber(request.phoneNumber())) {
            throw new RuntimeException("Phone number already registered");
        }

        String password = passwordEncoder.encode(request.password());
        String otp = generateVerificationCode();

        User user = new User(
                request.fullName(), request.phoneNumber(), password
        );
        user.setVerificationCode(otp);
        user.setVerificationCodeExpiresAt(LocalDateTime.now().plusMinutes(15));
        user.setEnabled(false);

        userRepository.save(user);
        notificationService.sendMessage(user.getPhoneNumber(), NotificationTemplates.verificationMessage(otp));

        return new RegisterResponse("Registration Successful, Please verify your phone number.");
    }

    public VerifyOtpResponse verifyOtp(VerifyOtpRequest request) {
        User user = userRepository.findByPhoneNumber(
                request.phoneNumber()).orElseThrow(() -> new RuntimeException("Invalid credentials")
        );

        if (!user.getVerificationCode().equals(request.verificationCode())) {
            throw new RuntimeException("Invalid or expired verification code");
        }

        if (user.getVerificationCodeExpiresAt().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Verification code has expired");
        }
        user.setEnabled(true);
        user.setVerificationCode(null);
        user.setVerificationCodeExpiresAt(null);
        userRepository.save(user);

        return  new VerifyOtpResponse("Phone number verified successfully");
    }

    public LoginResponse authenticateUser(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.phoneNumber(),request.password())
        );

        User user = (User) authentication.getPrincipal();

        if (!user.isEnabled()) {
            throw new RuntimeException("Account not verified. Please verify your account");
        }

        return new LoginResponse("Login successful", jwtService.generateToken(user));
    }

    public ResendOtpResponse resendOtp(ResendOtpRequest request) {
        String otp = generateVerificationCode();

        User user =  userRepository.findByPhoneNumber(
                request.phoneNumber()).orElseThrow(() -> new RuntimeException("Invalid credentials")
        );
        user.setVerificationCode(otp);
        user.setVerificationCodeExpiresAt(LocalDateTime.now().plusMinutes(15));

        userRepository.save(user);
        notificationService.sendMessage(user.getPhoneNumber(), NotificationTemplates.verificationMessage(otp));
        return new ResendOtpResponse("A new verification code has been sent to your phone number.");
    }
}
