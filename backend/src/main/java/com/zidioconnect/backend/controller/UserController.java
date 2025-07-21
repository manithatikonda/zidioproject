package com.zidioconnect.backend.controller;

import com.zidioconnect.backend.model.User;
import com.zidioconnect.backend.repository.UserRepository;
import com.zidioconnect.backend.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/profile")
    @PreAuthorize("hasAnyRole('STUDENT','ADMIN')")
    public ResponseEntity<?> getUserProfile(HttpServletRequest request) {
        try {
            String authHeader = request.getHeader("Authorization");

            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                return ResponseEntity.status(401).body("Unauthorized: Missing token");
            }

            String token = authHeader.substring(7);
            String email = jwtUtil.extractEmail(token);

            User user = userRepository.findByEmail(email)
                    .orElseThrow(() -> new RuntimeException("User not found"));

            return ResponseEntity.ok(Map.of(
                    "name", user.getName(),
                    "email", user.getEmail(),
                    "role", user.getRole()
            ));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(403).body("Something went wrong!");
        }
    }
}
