package com.poorna.JobTrackerApp.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.poorna.JobTrackerApp.dtos.AuthResponse;
import com.poorna.JobTrackerApp.dtos.LoginRequest;
import com.poorna.JobTrackerApp.dtos.RefreshRequest;
import com.poorna.JobTrackerApp.entity.RefreshToken;
import com.poorna.JobTrackerApp.service.RefreshTokenService;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;



@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private RefreshTokenService refreshTokenService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private com.poorna.JobTrackerApp.util.JwtUtil jwtUtil;
    @Autowired
    private UserDetailsService userDetailsService;
    @GetMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest loginRequest) {
        Authentication authentication=authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                loginRequest.getUsername(),
                loginRequest.getPassword()
            )
        );
        UserDetails userDetails=(UserDetails) authentication.getPrincipal();
        String accessToken=jwtUtil.generateToken(userDetails);
        String refreshToken=refreshTokenService.createToken(userDetails.getUsername());
        return new AuthResponse(accessToken, refreshToken);
    }
    @PostMapping("/refresh")
    public AuthResponse refreshToken(@RequestBody RefreshRequest refreshRequest) {
        RefreshToken refreshToken = refreshTokenService.verify(refreshRequest.getRefreshToken());
        UserDetails userDetails = userDetailsService.loadUserByUsername(refreshToken.getUsername());
        String accessToken=jwtUtil.generateToken(userDetails);
        String newRefreshToken=refreshTokenService.createToken(userDetails.getUsername());
        return new AuthResponse(accessToken, newRefreshToken);
    }
    @PostMapping("/logout")
    public void logout(@RequestBody RefreshRequest req) {
        refreshTokenService.revoke(req.getRefreshToken());
    }
}