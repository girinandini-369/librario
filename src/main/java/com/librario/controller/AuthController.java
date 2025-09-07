package com.librario.controller;

<<<<<<< HEAD
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
=======
import com.librario.entity.User;
import com.librario.dto.AuthRequest;
import com.librario.dto.MessageResponse;
import com.librario.repository.UserRepository;
import com.librario.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
>>>>>>> b878e07268c5607efc5e8614f31f94c1c274fef6
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserRepository userRepository;
<<<<<<< HEAD
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
=======
    private final UserService userService;
>>>>>>> b878e07268c5607efc5e8614f31f94c1c274fef6

    // ✅ Login
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody AuthRequest request) {
<<<<<<< HEAD
        User user = userRepository.findByEmailIgnoreCase(request.getEmail().trim())
                .orElse(null);
=======
        String cleanEmail = request.getEmail().trim();

        User user = userRepository.findByEmailIgnoreCase(cleanEmail).orElse(null);
>>>>>>> b878e07268c5607efc5e8614f31f94c1c274fef6

        if (user == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new MessageResponse("❌ User not found"));
        }

<<<<<<< HEAD
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
=======
        if (!userService.checkPassword(request.getPassword(), user.getPassword())) {
>>>>>>> b878e07268c5607efc5e8614f31f94c1c274fef6
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new MessageResponse("❌ Invalid password"));
        }

        return ResponseEntity.ok(new MessageResponse("✅ Login successful"));
    }
}
