package com.librario.controller;

import com.librario.entity.User;
import com.librario.dto.AuthRequest;
import com.librario.dto.MessageResponse;
import com.librario.repository.UserRepository;
import com.librario.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserRepository userRepository;
    private final UserService userService;

    // ✅ Login
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody AuthRequest request) {
        String cleanEmail = request.getEmail().trim();

        User user = userRepository.findByEmailIgnoreCase(cleanEmail).orElse(null);

        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new MessageResponse("❌ User not found"));
        }

        if (!userService.checkPassword(request.getPassword(), user.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new MessageResponse("❌ Invalid password"));
        }

        return ResponseEntity.ok(new MessageResponse("✅ Login successful"));
    }
}
