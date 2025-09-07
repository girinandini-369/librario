package com.librario.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "roles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

<<<<<<< HEAD
    @Column(unique = true, nullable = false)
=======
    @Column(nullable = false, unique = true)
>>>>>>> b878e07268c5607efc5e8614f31f94c1c274fef6
    private String roleName;
}
