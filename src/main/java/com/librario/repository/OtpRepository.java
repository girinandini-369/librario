package com.librario.repository;

import com.librario.entity.OtpCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface OtpRepository extends JpaRepository<OtpCode, Long> {

    @Transactional
    void deleteByEmail(String email);

    Optional<OtpCode> findByEmailAndOtp(String email, String otp);
}
