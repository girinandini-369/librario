package com.librario.controller;

import com.librario.dto.AuthRequest;
import com.librario.dto.MessageResponse;
import com.librario.entity.Role;
import com.librario.entity.User;
import com.librario.repository.RoleRepository;
import com.librario.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    // ✅ Register
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody AuthRequest request) {
        if (userRepository.findByEmailIgnoreCase(request.getEmail()).isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new MessageResponse("❌ Email already exists"));
        }

        // ✅ Get default role (ROLE_MEMBER)
        Role defaultRole = roleRepository.findByRoleName("ROLE_MEMBER")
                .orElseThrow(() -> new RuntimeException("❌ Default role not found in DB"));

        // ✅ Create new user
        User newUser = User.builder()
                .email(request.getEmail().trim())
                .name(request.getName().trim())
                .password(passwordEncoder.encode(request.getPassword())) // ✅ Fixed
                .role(defaultRole) // ✅ Assign role
                .build();

        userRepository.save(newUser);

        return ResponseEntity.ok(new MessageResponse("✅ User registered successfully"));
    }

    // ✅ Login
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody AuthRequest request) {
        User user = userRepository.findByEmailIgnoreCase(request.getEmail().trim())
                .orElse(null);

        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new MessageResponse("❌ User not found"));
        }

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new MessageResponse("❌ Invalid password"));
        }

        return ResponseEntity.ok(new MessageResponse("✅ Login successful"));
    }
}
