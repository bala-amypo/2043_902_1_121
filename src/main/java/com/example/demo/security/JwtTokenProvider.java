package com.example.demo.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenProvider {

    private Key key;
    private long EXPIRATION;

    // ✅ REQUIRED BY TESTCASES
    public JwtTokenProvider(String secret, int expirySeconds) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
        this.EXPIRATION = expirySeconds * 1000L;
    }

    // ✅ REQUIRED BY SPRING
    public JwtTokenProvider() {
        this.key = Keys.hmacShaKeyFor(
                "THIS_IS_A_256_BIT_SECRET_KEY_FOR_JWT_AUTHENTICATION"
                        .getBytes()
        );
        this.EXPIRATION = 24 * 60 * 60 * 1000;
    }

    public String generateToken(Long userId, String email, String role) {

        return Jwts.builder()
                .setSubject(email)
                .claim("id", userId)
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (JwtException e) {
            return false;
        }
    }

    public String getEmailFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public String getRoleFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .get("role", String.class);
    }

    public Long getUserIdFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .get("id", Long.class);
    }
}
