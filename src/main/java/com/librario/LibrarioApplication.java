package com.librario;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class LibrarioApplication {

    public static void main(String[] args) {
        SpringApplication.run(LibrarioApplication.class, args);
    }

    // ✅ Generate a bcrypt hash for testing existing users
    @Bean
    public CommandLineRunner demo(PasswordEncoder passwordEncoder) {
        return args -> {
            String rawPassword = "huma123"; // 👉 change this to the password you want
            String hash = passwordEncoder.encode(rawPassword);
            System.out.println("🔑 Generated hash for '" + rawPassword + "' → " + hash);
        };
    }
}
