package com.vulcan.class_management.controllers;

import com.vulcan.class_management.models.Role;
import com.vulcan.class_management.security.JwtService;
import com.vulcan.class_management.models.Users;
import com.vulcan.class_management.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.ArrayList;


import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> credentials) {
        String username = credentials.get("username");
        String password = credentials.get("password");

        /* Usr exists? */
        Users user = userRepository.findByUsername(username)
                .orElse(null);

        if (user == null || !passwordEncoder.matches(password, user.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "Credenciales incorrectas"));
        }

        /* GET All Roles */
        List<String> roles = user.getRoles().stream()
                .map(Role::getName) /* GETs `name` from each `Role` */
                .toList();

        /* Generate JWT */
        String token = jwtService.generateToken(username, roles);

        /* Returns token */
        return ResponseEntity.ok(Map.of("token", token));
    }

}
