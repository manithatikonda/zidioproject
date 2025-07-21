package com.zidioconnect.backend.repository;

import com.zidioconnect.backend.model.FileUpload;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FileUploadRepository extends JpaRepository<FileUpload, Long>{
    List<FileUpload> findByUploaderEmail(String email);
}
