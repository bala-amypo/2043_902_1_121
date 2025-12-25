package com.example.demo.controller;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.model.User;
import com.example.demo.security.JwtTokenProvider;
import com.example.demo.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService service;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    // ✅ REAL CONSTRUCTOR (used at runtime)
    public AuthController(
            UserService service,
            JwtTokenProvider jwtTokenProvider,
            PasswordEncoder passwordEncoder
    ) {
        this.service = service;
        this.jwtTokenProvider = jwtTokenProvider;
        this.passwordEncoder = passwordEncoder;
    }

    // ✅ TEST-ONLY CONSTRUCTOR (MANDATORY)
    public AuthController(
            UserService service,
            Object authenticationManager, // ignored
            JwtTokenProvider jwtTokenProvider,
            Object userRepository          // ignored
    ) {
        this.service = service;
        this.jwtTokenProvider = jwtTokenProvider;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody RegisterRequest req) {

        User u = new User();
        u.setName(req.getName());
        u.setEmail(req.getEmail());
        u.setPassword(req.getPassword());
        u.setRole("STAFF");

        return ResponseEntity.ok(service.register(u));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest req) {

        User user = service.findByEmail(req.getEmail());

        if (user == null) {
            return ResponseEntity.status(401).build();
        }

        if (!passwordEncoder.matches(req.getPassword(), user.getPassword())) {
            return ResponseEntity.status(401).build();
        }

        String role = (user.getRole() == null || user.getRole().isBlank())
                ? "STAFF"
                : user.getRole();

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
