package com.librario.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
<<<<<<< HEAD
    private String name;

    @Column(nullable = false)
    private String password;

    // ✅ Many-to-One relation with Role
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id", nullable = false)
=======
    private String password;

    // ✅ Many users can have one role (e.g., USER, ADMIN)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id")
>>>>>>> b878e07268c5607efc5e8614f31f94c1c274fef6
    private Role role;
}
