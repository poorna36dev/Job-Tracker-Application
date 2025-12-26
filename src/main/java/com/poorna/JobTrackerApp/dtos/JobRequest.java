package com.poorna.JobTrackerApp.dtos;

import com.poorna.JobTrackerApp.entity.ApplicationStatus;

public class JobRequest {
    private String title;
    private String description;
    private String location;
    private String company;
    private ApplicationStatus status;
    // Getters and setters
    public String getTitle() {
        return title;
    }

    public ApplicationStatus getStatus() {
        return status;
    }

    public void setStatus(ApplicationStatus status) {
        this.status = status;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
}