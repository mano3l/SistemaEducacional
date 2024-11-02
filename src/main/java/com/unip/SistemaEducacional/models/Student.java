package com.unip.SistemaEducacional.models;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Data
@Table(name = "Student")
// CREATE TABLE Student (id INT AUTO_INCREMENT PRIMARY KEY, cpf VARCHAR(255) UNIQUE NOT NULL, name VARCHAR(255), lastname VARCHAR(255), age INT, sex VARCHAR(255), phone VARCHAR(255), address VARCHAR(255))
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    // CREATE COLUMN id INT AUTO_INCREMENT PRIMARY KEY
    private int id;

    @Column(name="cpf", unique=true, nullable=false)
    // CREATE COLUMN cpf VARCHAR(255) UNIQUE NOT NULL
    private String cpf;

    @Column(name="name")
    // CREATE COLUMN name VARCHAR(255)
    private String name;

    @Column(name="lastname")
    // CREATE COLUMN lastname VARCHAR(255)
    private String lastName;

    @Column(name="age")
    // CREATE COLUMN age INT
    private int age;

    @Column(name="sex")
    // CREATE COLUMN sex VARCHAR(255)
    private String sex;

    @Column(name="phone")
    // CREATE COLUMN phone VARCHAR(255)
    private String phone;

    @Column(name="address")
    // CREATE COLUMN address VARCHAR(255)
    private String address;

    @OneToOne(mappedBy = "student")
    // ALTER TABLE Enrollment ADD CONSTRAINT FK_studentId FOREIGN KEY (studentId) REFERENCES Student(id)
    private Enrollment enrollment;
}