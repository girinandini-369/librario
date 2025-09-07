package com.librario.service;

import com.librario.entity.User;
import com.librario.repository.UserRepository;
import lombok.RequiredArgsConstructor;
<<<<<<< HEAD
import org.springframework.security.crypto.password.PasswordEncoder;
=======
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
>>>>>>> b878e07268c5607efc5e8614f31f94c1c274fef6
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
<<<<<<< HEAD
    private final PasswordEncoder passwordEncoder;

    /**
     * ✅ Update user password
     */
    public void updatePassword(String email, String newPassword) {
        User user = userRepository.findByEmailIgnoreCase(email)
                .orElseThrow(() -> new RuntimeException("❌ User not found with email: " + email));

        user.setPassword(passwordEncoder.encode(newPassword)); // Always encode password
=======
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public boolean checkPassword(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

    // ✅ Update password by email
    public void updatePassword(String email, String newPassword) {
        User user = userRepository.findByEmailIgnoreCase(email)
                .orElseThrow(() -> new RuntimeException("User not found with email: " + email));

        user.setPassword(passwordEncoder.encode(newPassword));
>>>>>>> b878e07268c5607efc5e8614f31f94c1c274fef6
        userRepository.save(user);
    }
}
