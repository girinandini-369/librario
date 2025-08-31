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
        // Generate 6-digit OTP (always zero-padded)
        String otp = String.format("%06d", ThreadLocalRandom.current().nextInt(0, 1_000_000));

        LocalDateTime now = LocalDateTime.now();

        // Delete old OTP for this email
        otpRepository.deleteByEmail(email);

        // Save new OTP in DB
        OtpCode otpCode = new OtpCode();
        otpCode.setEmail(email);
        otpCode.setOtp(otp);
        otpCode.setCreatedAt(now);
        otpCode.setExpiresAt(now.plusMinutes(otpExpirationMinutes));

        otpRepository.save(otpCode);

        // Send via email
        sendOtpEmail(email, otp);
    }

    private void sendOtpEmail(String toEmail, String otp) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(toEmail);
            helper.setSubject("Your OTP for Librario Password Reset");
            helper.setText(
                    "Hello,\n\n" +
                            "Your OTP is: " + otp + "\n\n" +
                            "This OTP will expire in " + otpExpirationMinutes + " minutes.\n\n" +
                            "If you didn’t request this, please ignore.\n\n" +
                            "Regards,\nLibrario Team"
            );

            mailSender.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException("Failed to send OTP email", e);
        }
    }

    @Transactional
    public boolean validateOtp(String email, String otp) {
        return otpRepository.findByEmailAndOtp(email, otp)
                .map(otpCode -> {
                    LocalDateTime now = LocalDateTime.now();
                    if (otpCode.getExpiresAt().isBefore(now)) {
                        otpRepository.delete(otpCode);
                        return false;
                    }
                    otpRepository.delete(otpCode); // OTP is single-use
                    return true;
                })
                .orElse(false);
    }
}
