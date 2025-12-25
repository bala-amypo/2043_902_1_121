package com.example.demo.controller;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.model.User;
import com.example.demo.security.JwtTokenProvider;
import com.example.demo.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService service;
    private final JwtTokenProvider jwtTokenProvider;

    public AuthController(UserService service, JwtTokenProvider jwtTokenProvider) {
        this.service = service;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody RegisterRequest req) {

        User u = new User();
        u.setName(req.name);
        u.setEmail(req.email);
        u.setPassword(req.password);
        u.setRole("STAFF");   // required

        return ResponseEntity.ok(service.register(u));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest req) {

        User user = service.findByEmail(req.getEmail());

        if (user == null) {
            return ResponseEntity.status(401).build();
        }

        // ✅ ABSOLUTE FIX — prevent 500
        String role = user.getRole();
        if (role == null || role.isBlank()) {
            role = "STAFF";
        }

        String token = jwtTokenProvider.generateToken(
                user.getId(),
                user.getEmail(),
                role
        );

        AuthResponse res = new AuthResponse();
        res.setToken(token);

        return ResponseEntity.ok(res);
    }
}
