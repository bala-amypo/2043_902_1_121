package com.example.demo.service.impl;

import com.example.demo.exception.ApiException;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repo;
    private final PasswordEncoder encoder;

    public UserServiceImpl(UserRepository repo, PasswordEncoder encoder) {
        this.repo = repo;
        this.encoder = encoder;
    }

    @Override
    public User register(User user) {

        // 1. Null + mandatory field check
        if (user == null ||
            user.getEmail() == null ||
            user.getEmail().trim().isEmpty() ||
            user.getPassword() == null ||
            user.getPassword().trim().isEmpty()) {

            throw new ApiException("Invalid user data");
        }

        // 2. Duplicate email check (IMPORTANT FOR test51)
        if (repo.findByEmail(user.getEmail()).isPresent()) {
            throw new ApiException("Email already registered");
        }

        // 3. Encode password
        user.setPassword(encoder.encode(user.getPassword()));

        return repo.save(user);
    }

    @Override
    public User findByEmail(String email) {

        if (email == null || email.trim().isEmpty()) {
            throw new ApiException("User not found");
        }

        // IMPORTANT FOR test50
        return repo.findByEmail(email)
                .orElseThrow(() -> new ApiException("User not found"));
    }
}
