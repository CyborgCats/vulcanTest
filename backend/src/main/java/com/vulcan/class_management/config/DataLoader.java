package com.vulcan.class_management.config;

import com.vulcan.class_management.models.Role;
import com.vulcan.class_management.models.Users;
import com.vulcan.class_management.repositories.RoleRepository;
import com.vulcan.class_management.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;

@Configuration
public class DataLoader {

    @Bean
    @Transactional
    public CommandLineRunner loadData(UserRepository userRepository,
                                      RoleRepository roleRepository,
                                      PasswordEncoder passwordEncoder) {
        return args -> {
            // Crear roles si no existen
            Role adminRole = roleRepository.findByName("ROLE_ADMIN")
                    .orElseGet(() -> {
                        Role role = new Role();
                        role.setName("ROLE_ADMIN");
                        return roleRepository.save(role);
                    });

            Role userRole = roleRepository.findByName("ROLE_USER")
                    .orElseGet(() -> {
                        Role role = new Role();
                        role.setName("ROLE_USER");
                        return roleRepository.save(role);
                    });

            // Crear usuarios si no existen
            if (userRepository.findByUsername("admin").isEmpty()) {
                Users admin = new Users();
                admin.setUsername("admin");
                admin.setPassword(passwordEncoder.encode("@dm1n"));
                admin.setRoles(Set.of(adminRole));
                userRepository.save(admin);
            }

            if (userRepository.findByUsername("user").isEmpty()) {
                Users user = new Users();
                user.setUsername("user");
                user.setPassword(passwordEncoder.encode("us3r"));
                user.setRoles(Set.of(userRole));
                userRepository.save(user);
            }
        };
    }
}
