package com.poorna.JobTrackerApp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.poorna.JobTrackerApp.entity.JobApplication;
import com.poorna.JobTrackerApp.entity.User;

import jakarta.transaction.Transactional;

@Repository
public interface JobApplicationRepository extends JpaRepository<JobApplication, Long> {
    @Query("select j from JobApplication j where j.user = :user")
    List<JobApplication> findAllByUser(@Param("user") User user);

    @Query("""
            select j
            from JobApplication j
            where j.id = :jobId
            and j.user = :user
            """)
    Optional<JobApplication> findByIdAndUser(
            @Param("jobId") Long jobId,
            @Param("user") User user);

    @Modifying
    @Transactional
    @Query("""
            delete
            from JobApplication j
            where j.id = :jobId
            and j.user = :user
            """)
    int deleteByIdAndUser(
            @Param("jobId") Long jobId,
            @Param("user") User user);

}
