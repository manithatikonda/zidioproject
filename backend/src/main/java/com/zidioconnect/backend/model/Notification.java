package com.zidioconnect.backend.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "notifications")

public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String message;
    private LocalDateTime timestamp;

    public Notification(){}
    public Notification(String email,String message, LocalDateTime timestamp ){
        this.email = email;
        this.message= message;
        this.timestamp=timestamp;
    }

    //getters and setters

    public Long getId() {
        return id;
    }
    public String getEmail(){
        return email;
    }
    public void setEmail(String email)
    {
        this.email=email;
    }
    public String getMessage(){
        return message;
    }
    public void setMessage(String message){
        this.message = message;
    }
    public LocalDateTime getTimestamp(){
        return timestamp;
    }
    public void setTimestamp(LocalDateTime timestamp)
    {
        this.timestamp = timestamp;
    }
}


