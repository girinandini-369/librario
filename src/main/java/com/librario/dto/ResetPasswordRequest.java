package com.librario.dto;

import lombok.Data;

@Data
public class ResetPasswordRequest {
    private String email;
    private String otp;           // OTP sent to email
    private String newPassword;   // New password after OTP verification
}
