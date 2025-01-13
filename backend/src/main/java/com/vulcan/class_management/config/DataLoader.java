package com.vulcan.class_management.config;

import com.vulcan.class_management.models.Role;
import com.vulcan.class_management.models.Users;
import com.vulcan.class_management.repositories.RoleRepository;
import com.vulcan.class_management.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@Configuration
public class DataLoader {

    @Bean
    public CommandLineRunner loadData(UserRepository userRepository,
                                      RoleRepository roleRepository,
                                      PasswordEncoder passwordEncoder) {
        return args -> {
            // Crear roles
            Role adminRole = new Role();
            adminRole.setName("ADMIN");
            Role userRole = new Role();
            userRole.setName("USER");

            roleRepository.saveAll(Set.of(adminRole, userRole));

            // Crear usuarios
            Users admin = new Users();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("@dm1n"));
            admin.setRoles(Set.of(adminRole));

            Users user = new Users();
            user.setUsername("user");
            user.setPassword(passwordEncoder.encode("us3r"));
            user.setRoles(Set.of(userRole));

            userRepository.saveAll(Set.of(admin, user));
        };
    }
}
