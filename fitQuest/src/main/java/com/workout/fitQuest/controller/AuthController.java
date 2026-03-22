package com.workout.fitQuest.controller;

import com.workout.fitQuest.dto.response.UserResponse;
import com.workout.fitQuest.entity.User;
import com.workout.fitQuest.service.AuthService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> register(
            @Valid @RequestBody RegisterRequest request) {
        String token = authService.register(
                request.getName(),
                request.getEmail(),
                request.getPassword()
        );
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Map.of("token", token));
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(
            @Valid @RequestBody LoginRequest request) {
        String token = authService.login(
                request.getEmail(),
                request.getPassword()
        );
        return ResponseEntity.ok(Map.of("token", token));
    }

    @GetMapping("/me")
    public UserResponse getCurrentUser(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return UserResponse.from(user);
    }

    // Request DTOs (small enough to keep here)
    @Getter
    @Setter
    static class RegisterRequest {
        @NotBlank(message = "Name is required")
        private String name;

        @NotBlank(message = "Email is required")
        @Email(message = "Invalid email format")
        private String email;

        @NotBlank(message = "Password is required")
        private String password;
    }

    @Getter
    @Setter
    static class LoginRequest {
        @NotBlank(message = "Email is required")
        @Email(message = "Invalid email format")
        private String email;

        @NotBlank(message = "Password is required")
        private String password;
    }
}