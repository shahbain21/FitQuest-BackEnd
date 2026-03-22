package com.workout.fitQuest.service;

import com.workout.fitQuest.entity.User;
import com.workout.fitQuest.exception.DuplicateResourceException;
import com.workout.fitQuest.exception.ResourceNotFoundException;
import com.workout.fitQuest.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
    }

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User", "email", email));
    }

    @Transactional
    public User createUser(String name, String email, String passwordHash) {
        if (userRepository.existsByEmail(email)) {
            throw new DuplicateResourceException("User", "email", email);
        }

        User user = User.builder()
                .name(name)
                .email(email)
                .passwordHash(passwordHash)
                .build();

        return userRepository.save(user);
    }

    @Transactional
    public User updateUser(Long id, String name, String email) {
        User user = getUserById(id);

        // If email is changing, check it's not taken
        if (!user.getEmail().equals(email) && userRepository.existsByEmail(email)) {
            throw new DuplicateResourceException("User", "email", email);
        }

        user.setName(name);
        user.setEmail(email);
        // Note: password changes should go through a separate method
        return userRepository.save(user);
    }

    @Transactional
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException("User", "id", id);
        }
        userRepository.deleteById(id);
    }
}