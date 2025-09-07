package com.librario.service;

import com.librario.entity.User;
import com.librario.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmailIgnoreCase(email)
<<<<<<< HEAD
                .orElseThrow(() -> new UsernameNotFoundException("❌ User not found with email: " + email));

        // Assuming user has one role
        String roleName = user.getRole().getRoleName().replace("ROLE_", "");
=======
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + email));
>>>>>>> b878e07268c5607efc5e8614f31f94c1c274fef6

        return org.springframework.security.core.userdetails.User
                .withUsername(user.getEmail())
                .password(user.getPassword())
<<<<<<< HEAD
                .roles(roleName) // Roles without "ROLE_" prefix
=======
                .roles(user.getRole().getRoleName())  // ✅ fixed: use roleName field
>>>>>>> b878e07268c5607efc5e8614f31f94c1c274fef6
                .build();
    }
}
