package com.vulcan.class_management.models;
import com.vulcan.class_management.models.Curso;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Alumno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private int edad;

    private String genero;

    @ManyToMany(mappedBy = "alumnos", cascade = CascadeType.ALL)
    private List<Curso> cursos;
}
