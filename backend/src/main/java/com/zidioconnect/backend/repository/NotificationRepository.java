package com.zidioconnect.backend.repository;

import com.zidioconnect.backend.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findByEmail(String email);
}
