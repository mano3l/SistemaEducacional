package com.unip.SistemaEducacional.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @OneToOne
    @JoinColumn(name = "idAluno", referencedColumnName = "id", nullable = false)
    @JsonIgnore
    private Student student;
    @Column(name="email")
    private String email;
    @ManyToOne
    @JoinColumn(name = "codigoCurso", referencedColumnName = "codigo", nullable = false)
    @JsonIgnore
    private Course course;
    @Column(name="dataMatricula")
    private LocalDate dataMatricula;
}
