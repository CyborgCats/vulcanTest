package com.vulcan.class_management.repositories;

import com.vulcan.class_management.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
