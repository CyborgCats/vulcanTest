package com.vulcan.class_management.repositories;

import com.vulcan.class_management.models.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, Long> {
}
