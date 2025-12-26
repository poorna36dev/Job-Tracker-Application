package com.poorna.JobTrackerApp.dtos;

public class UserRequest {
    private String username;
    private String email;
    private String password;
    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    private Boolean isActive;

    public UserRequest() {
    }

    public UserRequest(String username, String email, String password, Boolean isActive) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.isActive = isActive;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}