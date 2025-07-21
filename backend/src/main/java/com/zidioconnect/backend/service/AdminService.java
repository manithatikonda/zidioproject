package com.zidioconnect.backend.service;

import com.zidioconnect.backend.dto.UpdateStatusRequest;
import com.zidioconnect.backend.dto.UserInfoResponse;
import com.zidioconnect.backend.model.User;
import com.zidioconnect.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service

public class AdminService {

    @Autowired
    private UserRepository userRepository;

    public List<UserInfoResponse> getAllUsers() {
        List<User> users = userRepository.findAll();

        return users.stream()
                .filter(user -> !"ADMIN" .equalsIgnoreCase(user.getRole())) //skip admins
                .map(user -> new UserInfoResponse(user.getName(), user.getEmail(), user.getRole(), user.getStatus()))
                .collect(Collectors.toList());
    }

    public String updateUserStatus(UpdateStatusRequest request){
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setStatus(request.getStatus());
        userRepository.save(user);
        return "User status updated successfully";
    }
}
