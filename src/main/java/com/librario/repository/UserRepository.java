package com.librario.repository;

import com.librario.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // ✅ Case-insensitive search by email
    Optional<User> findByEmailIgnoreCase(String email);

    // ✅ Normal search by email
    Optional<User> findByEmail(String email);

    // ✅ Exists check
    boolean existsByEmail(String email);
}
