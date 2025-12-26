package com.poorna.JobTrackerApp.service;

import java.util.List;

import com.poorna.JobTrackerApp.dtos.JobRequest;
import com.poorna.JobTrackerApp.dtos.JobResponse;
import com.poorna.JobTrackerApp.entity.User;

public interface JobService {
    JobResponse createJob(JobRequest dto, User user);
    List<JobResponse> getJobs(User user);
    JobResponse updateJob(Long id, JobRequest dto, User user);
    void deleteJob(Long id, User user);
   
}
