package com.poorna.JobTrackerApp.util;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {
    private final String secretKey = "my-secert-key-that-is-very-long-1234567890@#$%\"";
    private final SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes());
    private final long validityInMilliseconds = 3600000;

    public String generateToken(UserDetails userDetails) {
        String userName=userDetails.getUsername();
        return Jwts.builder()
                .setSubject(userName)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + validityInMilliseconds))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public String extractUsername(String token) {
        Claims user = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
        return user.getSubject();
    }

    public boolean validateToken(String username, UserDetails userDetails, String token) {
        return username != null && userDetails != null &&
               username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        Claims user = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
        Date expiration = user.getExpiration();
        return expiration.before(new Date());
    }

}
