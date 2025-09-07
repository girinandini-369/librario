package com.librario.repository;

import com.librario.entity.OtpCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OtpRepository extends JpaRepository<OtpCode, Long> {

    // Delete old OTP by email
    void deleteByEmail(String email);

    // Find OTP for validation
    Optional<OtpCode> findByEmailAndOtp(String email, String otp);
}