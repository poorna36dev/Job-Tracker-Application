package com.poorna.JobTrackerApp.service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

import com.poorna.JobTrackerApp.entity.RefreshToken;
import com.poorna.JobTrackerApp.repository.RefreshTokenRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
@Transactional
public class RefreshTokenService {

    private static final long REFRESH_EXP_DAYS = 30;

    private final RefreshTokenRepository repo;

    public RefreshTokenService(RefreshTokenRepository repo) {
        this.repo = repo;
    }

    public String createToken(String username) {

        // Kill old tokens (1 device policy)
        repo.deleteByUsername(username);

        String token = UUID.randomUUID().toString();

        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setToken(token);
        refreshToken.setUsername(username);
        refreshToken.setExpiry(Instant.now().plus(REFRESH_EXP_DAYS, ChronoUnit.DAYS));
        refreshToken.setRevoked(false);

        repo.save(refreshToken);
        return token;
    }

    public RefreshToken verify(String token) {

        RefreshToken refreshToken = repo.findByToken(token)
                .orElseThrow(() -> new RuntimeException("Invalid refresh token"));

        if (refreshToken.isRevoked())
            throw new RuntimeException("Refresh token revoked");

        if (refreshToken.getExpiry().isBefore(Instant.now()))
            throw new RuntimeException("Refresh token expired");

        return refreshToken;
    }

    public void revoke(String token) {
        repo.findByToken(token).ifPresent(refreshToken -> {
            refreshToken.setRevoked(true);
            repo.save(refreshToken);
        });
    }
}

