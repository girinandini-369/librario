package com.librario.controller;

import com.librario.dto.OtpRequest;
import com.librario.dto.ResetPasswordRequest;
import com.librario.service.OtpService;
import com.librario.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/otp")
@RequiredArgsConstructor
public class OtpController {

    private final OtpService otpService;
    private final UserService userService;

    // Step 1: User requests OTP for password reset
    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestBody OtpRequest request) {
        if (!userService.existsByEmail(request.getEmail())) {
            return ResponseEntity.badRequest().body("Email not registered");
        }
        otpService.generateAndSendOtp(request.getEmail());
        return ResponseEntity.ok("OTP sent to your email");
    }

    // Step 2: User submits OTP and new password
    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestBody ResetPasswordRequest request) {
        boolean valid = otpService.validateOtp(request.getEmail(), request.getOtp());
        if (!valid) {
            return ResponseEntity.badRequest().body("Invalid or expired OTP");
        }
        userService.updatePassword(request.getEmail(), request.getNewPassword());
        return ResponseEntity.ok("Password reset successfully");
    }
}
