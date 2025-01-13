package com.vulcan.class_management.repositories;

import com.vulcan.class_management.models.Alumno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlumnoRepository extends JpaRepository<Alumno, Long>{
}
