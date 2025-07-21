package com.zidioconnect.backend.model;

import jakarta.persistence.*;

@Entity
public class JobApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String studentEmail;
    private String jobTitle;
    private String company;

    public JobApplication() {}

    public JobApplication(Long id, String studentEmail,String jobTitle, String company){
        this.id = id;
        this.studentEmail = studentEmail;
        this.jobTitle = jobTitle;
        this.company = company;
    }

    //getters and setters
    public Long getId() {return id; }
    public void setId(Long id) { this.id = id; }

    public String getStudentEmail() { return studentEmail; }
    public void setStudentEmail(String studentEmail) { this.studentEmail = studentEmail; }

    public String getJobTitle() { return jobTitle; }
    public void setJobTitle(String jobTitle) { this.jobTitle = jobTitle; }

    public String getCompany() { return company; }
    public void setCompany(String company) {this.company = company; }
}
