package com.librario.dto;

import lombok.Data;

@Data
public class AuthRequest {
    private String name;    // used only during registration
    private String email;
    private String password;
    private String role;    // ADMIN, LIBRARIAN, MEMBER
}
