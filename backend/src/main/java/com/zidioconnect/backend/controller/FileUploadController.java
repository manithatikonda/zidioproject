package com.zidioconnect.backend.controller;

import com.zidioconnect.backend.model.FileUpload;
import com.zidioconnect.backend.repository.FileUploadRepository;
import com.zidioconnect.backend.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/files")

public class FileUploadController {

    @Autowired
    private FileUploadRepository fileRepo;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/upload")
    @PreAuthorize("hasAnyRole('STUDENT','RECRUITER')")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file, HttpServletRequest request){
        String token = request.getHeader("Authorization").substring(7);
        String email = jwtUtil.extractEmail(token);

        FileUpload uploaded = new FileUpload(
                file.getOriginalFilename(),
                file.getContentType(),
                email,
                LocalDateTime.now()
        );
        fileRepo.save(uploaded);

        return ResponseEntity.ok("File uploaded successfully!!");
    }

    @GetMapping("/my")
    @PreAuthorize("hasAnyRole('STUDENT','RECRUITER')")
    public ResponseEntity<List<FileUpload>> getMyFiles(HttpServletRequest request){
        String token = request.getHeader("Authorization").substring(7);
        String email = jwtUtil.extractEmail(token);

        return ResponseEntity.ok(fileRepo.findByUploaderEmail(email));
    }
}
