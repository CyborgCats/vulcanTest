package com.vulcan.class_management.controllers;

import com.vulcan.class_management.models.Alumno;
import com.vulcan.class_management.services.AlumnoService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alumnos")
public class AlumnoController {

    private final AlumnoService alumnoService;

    public AlumnoController(AlumnoService alumnoService) {
        this.alumnoService = alumnoService;
    }

    @GetMapping
    public List<Alumno> getAllAlumnos() {
        return alumnoService.getAllAlumnos();
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Alumno saveAlumno(@RequestBody Alumno alumno) {
        return alumnoService.saveAlumno(alumno);
    }

    @DeleteMapping("/{id}")
    public void deleteAlumno(@PathVariable Long id) {
        alumnoService.deleteAlumno(id);
    }
}
