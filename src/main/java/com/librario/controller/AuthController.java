package com.librario.controller;

import com.librario.dto.AuthRequest;
import com.librario.dto.AuthResponse;
import com.librario.dto.MessageResponse;
import com.librario.entity.Role;
import com.librario.entity.User;
import com.librario.repository.RoleRepository;
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

    private final UserService userService;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    // ✅ Register endpoint
    @PostMapping("/register")
    public ResponseEntity<MessageResponse> registerUser(@RequestBody AuthRequest request) {
        String cleanEmail = request.getEmail().trim();

        if (userService.existsByEmail(cleanEmail)) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("❌ Email already in use"));
        }

        User user = new User();
        user.setName(request.getName());
        user.setEmail(cleanEmail);
        user.setPassword(request.getPassword()); // ✅ Leave raw password, encoding is in UserService

        Role role = roleRepository.findByRoleName(request.getRole())
                .orElseThrow(() -> new RuntimeException("❌ Role not found: " + request.getRole()));
        user.setRole(role);

        userService.saveUser(user);

        return ResponseEntity.ok(new MessageResponse("✅ User registered successfully"));
    }

    // ✅ Simple Login endpoint
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody AuthRequest request) {
        String cleanEmail = request.getEmail().trim();

        User user = userRepository.findByEmailIgnoreCase(cleanEmail)
                .orElse(null);

        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new MessageResponse("❌ User not found: " + cleanEmail));
        }

        if (!userService.checkPassword(request.getPassword(), user.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new MessageResponse("❌ Invalid credentials"));
        }

        return ResponseEntity.ok(new AuthResponse(
                "✅ Login successful",
                user.getRole().getRoleName()
        ));
    }
}
