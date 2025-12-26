package com.poorna.JobTrackerApp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.poorna.JobTrackerApp.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findAll();

    User findByEmail(String email);

    User findByIsActive(String username);
    User findByUsername(String username);
}
