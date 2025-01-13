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

        // Verifica si el usuario existe
        Users user = userRepository.findByUsername(username)
                .orElse(null);

        if (user == null || !passwordEncoder.matches(password, user.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", "Credenciales incorrectas"));
        }

        // Extrae los roles del usuario
        List<String> roles = user.getRoles().stream()
                .map(Role::getName) // Extrae el atributo `name` de cada objeto `Role`
                .toList();

        // Genera un token JWT
        String token = jwtService.generateToken(username, roles);

        // Retorna el token
        return ResponseEntity.ok(Map.of("token", token));
    }

}
