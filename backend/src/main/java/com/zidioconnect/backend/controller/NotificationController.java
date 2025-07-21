package com.zidioconnect.backend.controller;

import com.zidioconnect.backend.model.Notification;
import com.zidioconnect.backend.repository.NotificationRepository;
import com.zidioconnect.backend.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/notifications")

public class NotificationController {

    @Autowired
    private NotificationRepository notificationRepo;

    @Autowired
    private JwtUtil jwtUtil;

    //send notification only to a user
    @PostMapping("/send")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> sendNotification(@RequestBody Map<String, String> payload) {
        String email = payload.get("email");
        String message = payload.get("message");

       Notification notification = new Notification(email , message, LocalDateTime.now());
       notificationRepo.save(notification);

       return ResponseEntity.ok("Notification sent to : "+email);
    }

    // get logged in users notification
    @GetMapping("/my")
    @PreAuthorize("hasAnyRole('STUDENT','RECRUITER')")
    public ResponseEntity<List<Notification>> getMyNotification(HttpServletRequest request){
        String token = request.getHeader("Authorization").substring(7);
        String email = jwtUtil.extractEmail(token);

        List<Notification> notifications = notificationRepo.findByEmail(email);
        return ResponseEntity.ok(notifications);
    }
}
