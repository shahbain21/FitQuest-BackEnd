package com.workout.fitQuest.config;

import com.workout.fitQuest.entity.User;
import com.workout.fitQuest.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserRepository userRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        // 1. Get the Authorization header
        String authHeader = request.getHeader("Authorization");

        // 2. If no token, skip this filter
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        // 3. Extract the token (remove "Bearer " prefix)
        String token = authHeader.substring(7);

        // 4. Validate the token
        if (jwtService.isTokenValid(token)) {
            String email = jwtService.extractEmail(token);

            // 5. Load the user from the database
            User user = userRepository.findByEmail(email).orElse(null);

            if (user != null) {
                // 6. Tell Spring Security "this user is authenticated"
                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(
                                user, null, Collections.emptyList()
                        );
                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        // 7. Continue to the next filter / controller
        filterChain.doFilter(request, response);
    }
}
