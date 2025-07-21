package com.zidioconnect.backend.controller;

import com.zidioconnect.backend.model.User;
import com.zidioconnect.backend.payload.UpdateStatusRequest;
import com.zidioconnect.backend.payload.UserInfoResponse;
import com.zidioconnect.backend.repository.UserRepository;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/admin")
@PreAuthorize("hasRole('ADMIN')")
public class AdminController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users")
    public ResponseEntity<List<UserInfoResponse>> getAllUsers() {
        List<User> users = userRepository.findAll();

        List<UserInfoResponse> response = users.stream()
                .map(user -> new UserInfoResponse(
                        user.getName(),
                        user.getEmail(),
                        user.getRole(),
                        user.getStatus() != null ? user.getStatus() : "N/A"
                ))
                .collect(Collectors.toList());

        return ResponseEntity.ok(response);
    }

    @PutMapping("/user/status")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> updateUserStatus(@RequestBody UpdateStatusRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setStatus(request.getStatus());
        userRepository.save(user);

        return ResponseEntity.ok("Status updated for: " + user.getEmail());
    }

    @GetMapping("/dashboard")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminDashboard() {
        return "Welcome to Admin Dashboard!";
    }
}
