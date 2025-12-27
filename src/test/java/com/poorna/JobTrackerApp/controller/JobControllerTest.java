package com.poorna.JobTrackerApp.controller;

import com.poorna.JobTrackerApp.dtos.JobRequest;
import com.poorna.JobTrackerApp.dtos.JobResponse;
import com.poorna.JobTrackerApp.entity.ApplicationStatus;
import com.poorna.JobTrackerApp.entity.User;
import com.poorna.JobTrackerApp.service.JobServiceImplementation;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class JobControllerTest {

    @Test
    void createJob_delegatesToService() {
        JobServiceImplementation svc = Mockito.mock(JobServiceImplementation.class);
        JobController controller = new JobController(svc);

        JobRequest req = new JobRequest();
        req.setCompany("C");
        req.setTitle("T");
        req.setStatus(ApplicationStatus.APPLIED);

        User u = new User();

        JobResponse resp = new JobResponse();
        resp.setId(1L);
        when(svc.createJob(req, u)).thenReturn(resp);

        JobResponse out = controller.createJob(req, u);

        assertSame(resp, out);
        verify(svc, times(1)).createJob(req, u);
    }

    @Test
    void getJobs_delegatesToService() {
        JobServiceImplementation svc = Mockito.mock(JobServiceImplementation.class);
        JobController controller = new JobController(svc);

        User u = new User();
        JobResponse r = new JobResponse();
        r.setId(2L);
        when(svc.getJobs(u)).thenReturn(List.of(r));

        var out = controller.getJobs(u);

        assertEquals(1, out.size());
        assertEquals(2L, out.get(0).getId());
        verify(svc, times(1)).getJobs(u);
    }

    @Test
    void updateJob_delegatesToService() {
        JobServiceImplementation svc = Mockito.mock(JobServiceImplementation.class);
        JobController controller = new JobController(svc);

        JobRequest req = new JobRequest();
        req.setCompany("A");
        req.setTitle("B");
        User u = new User();

        JobResponse resp = new JobResponse();
        resp.setId(3L);
        when(svc.updateJob(5L, req, u)).thenReturn(resp);

        var out = controller.putMethodName(5L, req, u);

        assertEquals(3L, out.getId());
        verify(svc, times(1)).updateJob(5L, req, u);
    }
}
