package com.librario.repository;

import com.librario.entity.OtpCode;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface OtpRepository extends JpaRepository<OtpCode, Long> {
    Optional<OtpCode> findByEmailAndOtp(String email, String otp);
    void deleteByEmail(String email);
}
