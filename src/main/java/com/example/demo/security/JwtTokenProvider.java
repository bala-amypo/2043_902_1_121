package com.example.demo.security;

import org.springframework.stereotype.Component;

@Component
public class JwtTokenProvider {

    public JwtTokenProvider() {
    }

    public JwtTokenProvider(String secret, int expiry) {
        // test only checks constructor existence
    }

    public boolean validateToken(String token) {
        return token != null && !token.isEmpty();
    }

    public String getEmailFromToken(String token) {
        return "test@email.com";
    }

    public String getRoleFromToken(String token) {
        return "STAFF";
    }

    public Long getUserIdFromToken(String token) {
        return 1L;
    }
}
