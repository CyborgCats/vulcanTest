package com.vulcan.class_management.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data

public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private int cupoMaximo;

    @ManyToMany
    @JoinTable(
            name = "curso_alumno",
            joinColumns = @JoinColumn(name = "curso_id"),
            inverseJoinColumns = @JoinColumn(name = "alumno_id")
    )
    private List<Alumno> alumnos;
}
