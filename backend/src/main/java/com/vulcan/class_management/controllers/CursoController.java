package com.vulcan.class_management.controllers;

import com.vulcan.class_management.models.Curso;
import com.vulcan.class_management.services.CursoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cursos")
public class CursoController {

    private final CursoService cursoService;

    public CursoController(CursoService cursoService) {
        this.cursoService = cursoService;
    }

    @GetMapping
    public List<Curso> getAllCursos() {
        return cursoService.getallCursos();
    }

    @PostMapping
    public Curso saveCurso(@RequestBody Curso curso) {
        return cursoService.saveCurso(curso);
    }

    @DeleteMapping("/{id}")
    public void deleteCurso(@PathVariable Long id) {
        cursoService.deleteCurso(id);
    }
}
