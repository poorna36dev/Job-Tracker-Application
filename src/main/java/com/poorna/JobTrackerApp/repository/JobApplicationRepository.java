package com.poorna.JobTrackerApp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.poorna.JobTrackerApp.entity.JobApplication;

import jakarta.transaction.Transactional;

@Repository
public interface JobApplicationRepository extends JpaRepository<JobApplication, Long> {
    @Query("select j from JobApplication j where j.user.email = :email")
    List<JobApplication> findAllByUserEmail(@Param("email") String email);

    @Query("""
            select j
            from JobApplication j
            where j.id = :jobId
            and j.user.email = :email
            """)
    Optional<JobApplication> findByIdAndUserEmail(
            @Param("jobId") Long jobId,
            @Param("email") String email);

    @Modifying
    @Transactional
    @Query("""
            delete
            from JobApplication j
            where j.id = :jobId
            and j.user.email = :email
            """)
    int deleteByIdAndUserEmail(
            @Param("jobId") Long jobId,
            @Param("email") String email);

}
