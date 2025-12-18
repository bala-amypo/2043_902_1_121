// public class UserService{

// }
package com.example.demo.service;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.AuthResponse;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.exception.ApiException;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.security.JwtTokenProvider;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       JwtTokenProvider jwtTokenProvider) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    public void register(RegisterRequest request) {

        if (userRepository.findByEmail(request.getEmail()) != null) {
            throw new ApiException("email exists");
        }

        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole("STAFF");

        userRepository.save(user);
    }

    public AuthResponse login(AuthRequest request) {

        User user = userRepository.findByEmail(request.getEmail());
        if (user == null) {
            throw new ApiException("user not found");
        }

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new ApiException("invalid credentials");
        }

        String token = jwtTokenProvider.generateToken(
                user.getId(),
                user.getEmail(),
                user.getRole()
        );

        return new AuthResponse(token);
    }
}
