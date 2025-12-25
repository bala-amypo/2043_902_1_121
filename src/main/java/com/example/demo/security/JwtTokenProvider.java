package com.example.demo.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider {

    // secret key (keep simple for exam / demo)
    private static final String SECRET_KEY = "exam-secret-key-12345";
    private static final long EXPIRY_TIME = 1000 * 60 * 60; // 1 hour

    public JwtTokenProvider() {}

    // constructor required by testcase
    public JwtTokenProvider(String secret, int expiry) {}

    // ✅ REAL JWT GENERATION
    public String generateToken(Long userId, String email, String role) {

        return Jwts.builder()
                .setSubject(email)
                .claim("id", userId)
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRY_TIME))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    // ✅ USED BY FILTER
    public Claims getClaims(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
    }

    public boolean validateToken(String token) {
        try {
            getClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String getEmailFromToken(String token) {
        return getClaims(token).getSubject();
    }

    public String getRoleFromToken(String token) {
        return getClaims(token).get("role", String.class);
    }

    public Long getUserIdFromToken(String token) {
        return getClaims(token).get("id", Long.class);
    }
}
