package com.unip.SistemaEducacional.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "Enrollment")
public class Enrollment {
    @Id
    @Column(name="ra")
    private String ra;
    @ManyToOne
    @JoinColumn(name = "idAluno", referencedColumnName = "id", nullable = false)
    private Student student;
    @Column(name="email")
    private String email;
    @ManyToOne
    @JoinColumn(name = "codigoCurso", referencedColumnName = "codigo", nullable = false)
    private Course course;
    @Column(name="dataMatricula")
    private LocalDate dataMatricula;
}
