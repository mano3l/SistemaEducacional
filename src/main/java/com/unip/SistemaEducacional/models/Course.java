package com.unip.SistemaEducacional.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "Course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="codigo")
    private Integer codigoCurso;
    @Column(name="nomeCurso")
    private String nomeCurso;
    @Column(name="nivelDoCurso")
    private String nivelDoCurso;
    @Column(name="duracao")
    private String duracao;
    @Column(name="horario")
    private String horario;
    @Column(name="descricao")
    private String descricao;

    @OneToMany(mappedBy = "course")
    private List<Enrollment> enrollments;
}

