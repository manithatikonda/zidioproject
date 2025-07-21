package com.zidioconnect.backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "recruiters")

public class Recruiter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String email;
    private String company;

    //constructors
    public Recruiter(){}

    public Recruiter(String name, String email,String company){
        this.name = name;
        this.email= email;
        this.company= company;

    }

    public Long getId(){ return id;}
    public String getName(){ return name; }
    public String getEmail(){ return email; }
    public String getCompany() { return company; }

    public void setId(Long id) { this.id = id; }
    public void setName(String name) {this.name = name;}
    public void setEmail(String email) {this.email = email;}
    public void setCompany(String company) {this.company = company;}
}
