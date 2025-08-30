package com.librario.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Data  // ✅ Lombok will generate getters/setters, toString, equals, hashCode
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(nullable = false, unique = true)
    private String email;   // ✅ used in CustomUserDetailsService

    @Column(nullable = false)
    private String password;  // ✅ used in CustomUserDetailsService

    @ManyToOne(fetch = FetchType.EAGER)   // ✅ always load role with user
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;  // ✅ used in CustomUserDetailsService

    private String status;  // ACTIVE, INACTIVE

    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt = LocalDateTime.now();
}
