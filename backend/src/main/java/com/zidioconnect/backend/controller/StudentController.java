package com.zidioconnect.backend.controller;

import com.zidioconnect.backend.dto.StudentDto;
import com.zidioconnect.backend.model.JobApplication;
import com.zidioconnect.backend.service.StudentService;
import com.zidioconnect.backend.repository.JobApplicationRepository;
import com.zidioconnect.backend.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/student")
@PreAuthorize("hasRole('STUDENT')")
public class StudentController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private StudentService studentService;

    @Autowired
    private JobApplicationRepository jobAppRepo;

    // Get Profile
    @GetMapping("/profile")
    public ResponseEntity<StudentDto> getStudentProfile(HttpServletRequest request) {
        String email = extractEmailFromRequest(request);
        return ResponseEntity.ok(studentService.getProfileByEmail(email));
    }

    // Update Profile
    @PostMapping("/profile")
    public ResponseEntity<String> updateStudentProfile(@RequestBody StudentDto dto, HttpServletRequest request) {
        String email = extractEmailFromRequest(request);
        dto.setEmail(email);
        return ResponseEntity.ok(studentService.saveOrUpdateStudent(dto));
    }

    //  Get Applied Jobs
    @GetMapping("/jobs")
    public ResponseEntity<?> getStudentJobs(HttpServletRequest request) {
        String email = extractEmailFromRequest(request);
        return ResponseEntity.ok(jobAppRepo.findByStudentEmail(email));
    }

    // Apply to a Job
    @PostMapping("/job/apply")
    public ResponseEntity<String> applyToJob(@RequestBody Map<String, String> jobDetails,
                                             HttpServletRequest request) {
        String email = extractEmailFromRequest(request);

        JobApplication jobApp = new JobApplication();
        jobApp.setStudentEmail(email);
        jobApp.setJobTitle(jobDetails.get("jobTitle"));
        jobApp.setCompany(jobDetails.get("company"));

        jobAppRepo.save(jobApp);

        return ResponseEntity.ok("Job applied successfully!");
    }

    // Extract email from token
    private String extractEmailFromRequest(HttpServletRequest request) {
        String token = request.getHeader("Authorization").substring(7);
        return jwtUtil.extractEmail(token);
    }
}
