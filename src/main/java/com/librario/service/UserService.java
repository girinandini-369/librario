package com.librario.service;

import com.librario.entity.User;
import com.librario.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * ✅ Update user password
     */
    public void updatePassword(String email, String newPassword) {
        User user = userRepository.findByEmailIgnoreCase(email)
                .orElseThrow(() -> new RuntimeException("❌ User not found with email: " + email));

        user.setPassword(passwordEncoder.encode(newPassword)); // Always encode password
        userRepository.save(user);
    }
}
