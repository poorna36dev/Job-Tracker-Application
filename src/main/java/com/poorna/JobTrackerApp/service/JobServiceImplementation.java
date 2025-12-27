package com.poorna.JobTrackerApp.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.poorna.JobTrackerApp.dtos.JobRequest;
import com.poorna.JobTrackerApp.dtos.JobResponse;
import com.poorna.JobTrackerApp.entity.JobApplication;
import com.poorna.JobTrackerApp.entity.User;
import com.poorna.JobTrackerApp.repository.JobApplicationRepository;

import jakarta.transaction.Transactional;
@Service
@Transactional
public class JobServiceImplementation implements JobService {
    private JobApplicationRepository    jobRepository;

    public JobServiceImplementation(JobApplicationRepository jobRepository) {
        this.jobRepository = jobRepository;
    }
    @Override
    public JobResponse createJob(JobRequest dto, User user) {
        JobApplication jobApplication = new JobApplication();
        jobApplication.setCompanyName(dto.getCompany());
        jobApplication.setJobTitle(dto.getTitle());
        jobApplication.setStatus(dto.getStatus());
        jobApplication.setDescription(dto.getDescription());
        jobApplication.setLocation(dto.getLocation());
        jobApplication.setApplicationDate(LocalDate.now());
        jobApplication.setUser(user);
        System.out.println(jobApplication.getDescription());
        System.out.println(jobApplication.getLocation());
        return mapToDto(jobRepository.save(jobApplication));
    }

    @Override
    public void deleteJob(Long id, User user) {
        int deleted = 0;
        if(jobRepository.findByIdAndUser(id, user).isPresent()) {
           deleted = jobRepository.deleteByIdAndUser(id, user);
        }
        else if(deleted == 0) {
            throw new RuntimeException("Job not found or unauthorized");
        }
    }

    @Override
    public List<JobResponse> getJobs(User user) {
        return jobRepository.findAllByUser(user).stream()
                .map(this::mapToDto)
                .toList();
    }

    @Override
    public JobResponse updateJob(Long id, JobRequest dto, User user) {
        JobApplication jobApplication=jobRepository.findByIdAndUser(id, user)
            .orElseThrow(() -> new RuntimeException("Job not found or unauthorized"));
            jobApplication.setCompanyName(dto.getCompany());
            jobApplication.setJobTitle(dto.getTitle());
            jobApplication.setStatus(dto.getStatus());
            return mapToDto(jobRepository.save(jobApplication));
        
    }
    
    private JobResponse mapToDto(JobApplication jobApplication) {
        JobResponse response = new JobResponse();
        response.setId(jobApplication.getId());
        response.setCompany(jobApplication.getCompanyName());
        response.setTitle(jobApplication.getJobTitle());
        response.setStatus(jobApplication.getStatus());
        response.setAppliedDate(jobApplication.getApplicationDate());
        response.setDescription(jobApplication.getDescription());
        response.setLocation(jobApplication.getLocation());
        return response;
    }
    
    
}
