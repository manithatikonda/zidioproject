package com.zidioconnect.backend.service;

import com.zidioconnect.backend.dto.RegisterRequest;
import com.zidioconnect.backend.dto.LoginRequest;

import com.zidioconnect.backend.util.JwtUtil;
import com.zidioconnect.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

    import com.zidioconnect.backend.dto.RegisterRequest;
    import com.zidioconnect.backend.model.User;
    import com.zidioconnect.backend.repository.UserRepository;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
    import org.springframework.stereotype.Service;

    @Service
    public class AuthService {

        @Autowired
        private UserRepository userRepository;

        @Autowired
        private JwtUtil jwtUtil;

        private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        public String register(RegisterRequest request) {
            //check if user already exists
            if (userRepository.findByEmail(request.getEmail()).isPresent()) {
                return "Email already registered!";
            }

            //encrypt password
            String hashedPassword = passwordEncoder.encode(request.getPassword());

            //create and save user
            User user = new User(request.getName(), request.getEmail(), hashedPassword, request.getRole());
            userRepository.save(user);

            return "Registration successful !";

        }
        public String login(LoginRequest request){
            User user = userRepository.findByEmail(request.getEmail())
                    .orElseThrow(() -> new RuntimeException("User not found"));
            boolean passwordMatch = passwordEncoder.matches(request.getPassword(), user.getPassword());

            if(!passwordMatch){
                throw new RuntimeException("Invalid password");
            }

            //Generate JWT token using user Email
            return jwtUtil.generateToken(user.getEmail(), user.getRole());
        }
    }

