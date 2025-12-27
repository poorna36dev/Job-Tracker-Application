package com.poorna.JobTrackerApp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poorna.JobTrackerApp.entity.RefreshToken;
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByToken(String token);
    void deleteByToken(String token);
    void deleteByUsername(String username);
}
