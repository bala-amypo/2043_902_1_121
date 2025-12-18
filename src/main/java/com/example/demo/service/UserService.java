package com.example.demo.service;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.dto.RegisterRequest;

public interface UserService {

    void register(RegisterRequest request);

    AuthResponse login(AuthRequest request);
}
