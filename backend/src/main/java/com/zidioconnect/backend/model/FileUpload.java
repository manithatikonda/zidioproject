package com.zidioconnect.backend.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "file_uploads")

public class FileUpload {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fileName;
    private String fileType;
    private String uploaderEmail;
    private LocalDateTime uploadTime;

    public FileUpload() {}

    public FileUpload(String fileName,String fileType,String uploaderEmail, LocalDateTime uploadTime){
        this.fileName = fileName;
        this.fileType =  fileType;
        this.uploaderEmail= uploaderEmail;
        this.uploadTime = uploadTime;
    }

    //Getters and setters
    public Long getId(){ return id; }

    public String getFileName(){ return fileName; }
    public void setFileName(String fileName ){
        this.fileName=fileName;
    }
    public String getFileType(){ return fileType; }
    public void setFileType(String fileType){
        this.fileType = fileType;
    }
    public String getUploaderEmail(){ return uploaderEmail; }
    public void setUploaderEmail(String uploaderEmail){
        this.uploaderEmail = uploaderEmail;
    }
    public LocalDateTime getUploadTime(){ return uploadTime; }
    public void setUploadTime(LocalDateTime uploadTime){
        this.uploadTime = uploadTime;
    }
}
