package com.zidioconnect.backend.dto;

public class UserInfoResponse {
    private String name;
    private String email;
    private String role;
    private String status;

    public UserInfoResponse(){}

    public UserInfoResponse(String name,String email, String role, String status){
        this.name = name;
        this.email = email;
        this.role = role;
        this.status = status;
    }

    public String getEmail(){
        return email;
    }

    public String getRole(){
        return role;
    }

    public String getStatus(){
        return status;
    }
}
