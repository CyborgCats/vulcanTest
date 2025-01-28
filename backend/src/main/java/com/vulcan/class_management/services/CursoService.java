package com.vulcan.class_management.services;

import com.vulcan.class_management.models.Alumno;
import com.vulcan.class_management.models.Curso;
import com.vulcan.class_management.repositories.AlumnoRepository;
import com.vulcan.class_management.repositories.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CursoService {
    private final CursoRepository cursoRepository;

    @Autowired
    private AlumnoRepository alumnoRepository;

    public Curso asignarAlumno(Long cursoId, Long alumnoId) {
        Curso curso = cursoRepository.findById(cursoId)
                .orElseThrow(() -> new RuntimeException("Curso no encontrado"));
        Alumno alumno = alumnoRepository.findById(alumnoId)
                .orElseThrow(() -> new RuntimeException("Alumno no encontrado"));

        if (!curso.puedeAgregarAlumno()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El curso ha alcanzado su límite de cupo");
        }

        curso.getAlumnos().add(alumno);
        alumno.getCursos().add(curso);

        cursoRepository.save(curso);
        alumnoRepository.save(alumno);

        return curso;
    }

    public CursoService(CursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }

    public List<Curso> getallCursos() {
        return cursoRepository.findAll();
    }

    public Curso saveCurso(Curso curso) {
        return cursoRepository.save(curso);
    }

    public void deleteCurso(Long id) {
        cursoRepository.deleteById(id);
    }
}
