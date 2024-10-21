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
    private int codigoPrograma;
    @Column(name="nomeCurso")
    private String nomePrograma;
    @Column(name="nivelDoCurso")
    private String nivelDoPrograma;
    @Column(name="duracao")
    private String duracao;
    @Column(name="horario")
    private String horario;
    @Column(name="descricao")
    private String descricao;

    @OneToMany(mappedBy = "course")
    private List<Enrollment> enrollments;
}

