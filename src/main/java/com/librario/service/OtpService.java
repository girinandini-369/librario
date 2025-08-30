package com.librario.service;

import com.librario.entity.OtpCode;
import com.librario.repository.OtpRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.concurrent.ThreadLocalRandom;

@Service
@RequiredArgsConstructor
public class OtpService {

    private final OtpRepository otpRepository;
    private final JavaMailSender mailSender;

    @Value("${otp.expiration.minutes:5}")
    private int otpExpirationMinutes;

    @Transactional
    public void generateAndSendOtp(String email) {
        // Generate 6-digit OTP, including leading zeros
        String otp = String.format("%06d", ThreadLocalRandom.current().nextInt(1_000_000));

        LocalDateTime now = LocalDateTime.now();

        OtpCode otpCode = new OtpCode();
        otpCode.setEmail(email);
        otpCode.setOtp(otp);
        otpCode.setCreatedAt(now);
        otpCode.setExpiresAt(now.plusMinutes(otpExpirationMinutes));

        otpRepository.deleteByEmail(email); // Remove old OTPs
        otpRepository.save(otpCode);

        sendOtpEmail(email, otp);
    }

    private void sendOtpEmail(String toEmail, String otp) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(toEmail);
            helper.setSubject("Your OTP for Librario Password Reset");
            helper.setText("Your OTP is: " + otp + ". It will expire in " + otpExpirationMinutes + " minutes.");
            mailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException("Failed to send OTP email", e);
        }
    }

    @Transactional
    public boolean validateOtp(String email, String otp) {
        OtpCode otpCode = otpRepository.findByEmailAndOtp(email, otp)
                .orElse(null);
        if (otpCode == null) return false;

        LocalDateTime now = LocalDateTime.now();

        if (otpCode.getExpiresAt().isBefore(now)) {
            otpRepository.delete(otpCode);
            return false;
        }
        otpRepository.delete(otpCode);
        return true;
    }
}
