package com.zidioconnect.backend.controller;


import com.zidioconnect.backend.model.Recruiter;
import com.zidioconnect.backend.service.RecruiterService;
import com.zidioconnect.backend.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/recruiter")
@PreAuthorize("hasRole('RECRUITER')")

public class RecruiterController {
    @Autowired
    private RecruiterService recruiterService;

    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping("/profile")
    public ResponseEntity<Recruiter> getRecruiterProfile(HttpServletRequest request) {
        String token = request.getHeader("Authorization").substring(7);
        String email = jwtUtil.extractEmail(token);

        Recruiter recruiter = recruiterService.getRecruiterByEmail(email);
        return ResponseEntity.ok(recruiter); // ✅ works now
    }


    @PostMapping("/profile")
    public ResponseEntity<String> saveOrUpdateRecruiter(@RequestBody Recruiter recruiter,HttpServletRequest request) {
        String token = request.getHeader("Authorization").substring(7);
        String email = jwtUtil.extractEmail(token);
        recruiter.setEmail(email);
        return ResponseEntity.ok(recruiterService.saveorUpdateRecruiter(recruiter));

    }
}
