package com.poorna.JobTrackerApp.dtos;

import java.time.LocalDate;

import com.poorna.JobTrackerApp.entity.ApplicationStatus;

public class JobResponse {
    private Long id;
    private String title;
    private String description;
    private String location;
    private String company;
    private ApplicationStatus status;
    private LocalDate appliedDate;
    public ApplicationStatus getStatus() {
        return status;
    }

    public LocalDate getAppliedDate() {
        return appliedDate;
    }

    public void setAppliedDate(LocalDate appliedDate) {
        this.appliedDate = appliedDate;
    }

    public void setStatus(ApplicationStatus status) {
        this.status = status;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
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