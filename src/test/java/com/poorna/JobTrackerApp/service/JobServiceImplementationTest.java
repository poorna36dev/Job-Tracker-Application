package com.poorna.JobTrackerApp.service;

import com.poorna.JobTrackerApp.dtos.JobRequest;
import com.poorna.JobTrackerApp.dtos.JobResponse;
import com.poorna.JobTrackerApp.entity.ApplicationStatus;
import com.poorna.JobTrackerApp.entity.JobApplication;
import com.poorna.JobTrackerApp.entity.User;
import com.poorna.JobTrackerApp.repository.JobApplicationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class JobServiceImplementationTest {

    @Mock
    private JobApplicationRepository jobRepository;

    @InjectMocks
    private JobServiceImplementation service;

    @Test
    void createJob_savesAndMaps() {
        JobRequest req = new JobRequest();
        req.setCompany("Acme");
        req.setTitle("Engineer");
        req.setStatus(ApplicationStatus.APPLIED);

        User user = new User();
        user.setId(1L);

        JobApplication saved = new JobApplication();
        saved.setId(10L);
        saved.setCompanyName("Acme");
        saved.setJobTitle("Engineer");
        saved.setStatus(ApplicationStatus.APPLIED);
        saved.setApplicationDate(LocalDate.now());
        saved.setUser(user);

        when(jobRepository.save(any(JobApplication.class))).thenReturn(saved);

        JobResponse resp = service.createJob(req, user);

        assertNotNull(resp);
        assertEquals(10L, resp.getId());
        assertEquals("Acme", resp.getCompany());
        assertEquals("Engineer", resp.getTitle());
        assertEquals(ApplicationStatus.APPLIED, resp.getStatus());
        verify(jobRepository, times(1)).save(any(JobApplication.class));
    }

    @Test
    void getJobs_mapsAll() {
        User user = new User();
        user.setId(2L);

        JobApplication j = new JobApplication();
        j.setId(11L);
        j.setCompanyName("XCorp");
        j.setJobTitle("Dev");
        j.setStatus(ApplicationStatus.INTERVIEW);
        j.setApplicationDate(LocalDate.now());
        j.setUser(user);

        when(jobRepository.findAllByUser(user)).thenReturn(List.of(j));

        List<JobResponse> res = service.getJobs(user);

        assertEquals(1, res.size());
        assertEquals(11L, res.get(0).getId());
        assertEquals("XCorp", res.get(0).getCompany());
    }

    @Test
    void updateJob_success() {
        User user = new User();
        user.setId(3L);

        JobRequest req = new JobRequest();
        req.setCompany("NewCo");
        req.setTitle("Senior Dev");
        req.setStatus(ApplicationStatus.OFFER);

        JobApplication existing = new JobApplication();
        existing.setId(20L);
        existing.setCompanyName("OldCo");
        existing.setJobTitle("Dev");
        existing.setStatus(ApplicationStatus.APPLIED);
        existing.setUser(user);

        JobApplication saved = new JobApplication();
        saved.setId(20L);
        saved.setCompanyName("NewCo");
        saved.setJobTitle("Senior Dev");
        saved.setStatus(ApplicationStatus.OFFER);
        saved.setUser(user);

        when(jobRepository.findByIdAndUser(20L, user)).thenReturn(Optional.of(existing));
        when(jobRepository.save(any(JobApplication.class))).thenReturn(saved);

        JobResponse resp = service.updateJob(20L, req, user);

        assertEquals(20L, resp.getId());
        assertEquals("NewCo", resp.getCompany());
        assertEquals("Senior Dev", resp.getTitle());
        assertEquals(ApplicationStatus.OFFER, resp.getStatus());
    }

    @Test
    void updateJob_notFound_throws() {
        User user = new User();
        user.setId(4L);

        JobRequest req = new JobRequest();

        when(jobRepository.findByIdAndUser(99L, user)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> service.updateJob(99L, req, user));
    }

}
