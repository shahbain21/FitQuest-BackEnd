package com.workout.fitQuest.repository;

import com.workout.fitQuest.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    // SELECT * FROM app_user WHERE email = ?
    Optional<User> findByEmail(String email);

    // SELECT COUNT(*) > 0 FROM app_user WHERE email = ?
    boolean existsByEmail(String email);
}