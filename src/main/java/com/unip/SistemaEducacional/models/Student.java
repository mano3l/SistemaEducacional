package com.unip.SistemaEducacional.models;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Data
@Table(name = "Student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    @Column(name="cpf", unique=true, nullable=false)
    private String cpf;
    @Column(name="nome")
    private String nome;
    @Column(name="sobrenome")
    private String sobrenome;
    @Column(name="idade")
    private int idade;
    @Column(name="sexo")
    private String sexo;
    @Column(name="telefone")
    private String telefone;
    @Column(name="endereco")
    private String endereco;

    @OneToOne(mappedBy = "student")
    private Enrollment enrollment;
}
