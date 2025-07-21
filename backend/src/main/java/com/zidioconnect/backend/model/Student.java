package com.zidioconnect.backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "students")

public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String course;
    private String university;
    private String resumeUrl;

    //constructors
    public Student() {}

    public Student(String name,String email,String course,String university,String resumeUrl){
        this.name = name;
        this.email= email;
        this.course = course;
        this.university=university;
        this.resumeUrl=resumeUrl;
    }

    //Getters and Setters
    public Long getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name=name;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getCourse(){
        return course;
    }
    public void setCourse(String course){
        this.course = course;
    }

    public String getUniversity(){
        return university;
    }
    public void setUniversity(String university){
        this.university = university;
    }
    public String getResumeUrl(){
        return resumeUrl;
    }
    public void setResumeUrl(String resumeUrl){
        this.resumeUrl = resumeUrl;
    }

}
