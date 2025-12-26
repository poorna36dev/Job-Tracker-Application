package com.poorna.JobTrackerApp.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poorna.JobTrackerApp.dtos.JobRequest;
import com.poorna.JobTrackerApp.dtos.JobResponse;
import com.poorna.JobTrackerApp.entity.User;
import com.poorna.JobTrackerApp.service.JobServiceImplementation;


import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/jobs")
public class JobController {
    private JobServiceImplementation jobServiceImplementation;
    public JobController(JobServiceImplementation jobServiceImplementation) {
        this.jobServiceImplementation = jobServiceImplementation;
    }
    @PostMapping("/create")
    public JobResponse createJob(@RequestBody @Validated JobRequest jobRequest,@AuthenticationPrincipal User user) {
        
        return jobServiceImplementation.createJob(jobRequest,user);
    }
    @GetMapping("/getJobs")
    public List<JobResponse> getJobs(@AuthenticationPrincipal User user) {
        return jobServiceImplementation.getJobs(user);
    }
    @PutMapping("/Update/{id}")
    public JobResponse putMethodName(@PathVariable Long id, @RequestBody JobRequest jobRequest, @AuthenticationPrincipal User user) {
        return jobServiceImplementation.updateJob(id, jobRequest,user);
    } 
}
