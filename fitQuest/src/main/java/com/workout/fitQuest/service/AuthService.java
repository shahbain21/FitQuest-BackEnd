package com.workout.fitQuest.service;

import com.workout.fitQuest.config.JwtService;
import com.workout.fitQuest.entity.User;
import com.workout.fitQuest.exception.DuplicateResourceException;
import com.workout.fitQuest.exception.ResourceNotFoundException;
import com.workout.fitQuest.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Transactional
    public String register(String name, String email, String password) {
        // 1. Check if email is taken
        if (userRepository.existsByEmail(email)) {
            throw new DuplicateResourceException("User", "email", email);
        }

        // 2. Create user with hashed password
        User user = User.builder()
                .name(name)
                .email(email)
                .passwordHash(passwordEncoder.encode(password))
                .build();

        user = userRepository.save(user);

        // 3. Return JWT token
        return jwtService.generateToken(user.getId(), user.getEmail());
    }

    public String login(String email, String password) {
        // 1. Find user
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User", "email", email));

        // 2. Check password
        if (!passwordEncoder.matches(password, user.getPasswordHash())) {
            throw new RuntimeException("Invalid password");
        }

        // 3. Return JWT token
        return jwtService.generateToken(user.getId(), user.getEmail());
    }

    public User getCurrentUser(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User", "email", email));
    }
}