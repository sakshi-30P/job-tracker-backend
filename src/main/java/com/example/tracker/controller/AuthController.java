package com.example.tracker.controller;

import com.example.tracker.model.User;
import com.example.tracker.repository.UserRepository;
import com.example.tracker.security.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserRepository repo;

    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody User request) {

        try {
            authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(), request.getPassword()
                    )
            );
        } catch (AuthenticationException e) {
            throw new RuntimeException("Invalid Credentials");
        }

        User user = repo.findByEmail(request.getEmail()).get();
        String token = jwtUtil.generateToken(user.getEmail());

        return Map.of(
                "token", token,
                "user", user
        );
    }
}
