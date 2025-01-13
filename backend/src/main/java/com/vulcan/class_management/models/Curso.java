package com.vulcan.class_management.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Data
@Table(name = "cursos")
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private int cupoMaximo;

    @ManyToMany
    @JoinTable(
            name = "curso_alumnos",
            joinColumns = @JoinColumn(name = "curso_id"),
            inverseJoinColumns = @JoinColumn(name = "alumno_id")
    )
    @JsonManagedReference // Marca esta parte de la relación como principal para evitar recursión infinita
    private Set<Alumno> alumnos = new HashSet<>();

    public boolean puedeAgregarAlumno() {
        return alumnos.size() < cupoMaximo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id); // Usar solo el ID para el hash
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Curso other = (Curso) obj;
        return id != null && id.equals(other.id); // Comparar solo por ID
    }
}
