package com.zidioconnect.backend.controller;

import com.zidioconnect.backend.service.ResumeUploadService;
import jakarta.servlet.http.HttpServletRequest;
import com.zidioconnect.backend.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/resume")
public class ResumeUploadController {

    @Autowired
    private ResumeUploadService resumeUploadService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/upload")
    @PreAuthorize("hasAnyRole('STUDENT','RECRUITER')")
    public ResponseEntity<?> uploadResume(@RequestParam("file")MultipartFile file,HttpServletRequest request){
        try {
            String url = resumeUploadService.uploadResume(file);
            return ResponseEntity.ok("Resume uploaded to: " + url);
        }
        catch (Exception e){
            return ResponseEntity.badRequest().body("Upload failed:" + e.getMessage());
        }
    }


}
