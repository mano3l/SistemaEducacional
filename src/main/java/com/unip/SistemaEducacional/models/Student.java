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

    private int id;

    @Column(name="cpf", unique=true, nullable=false)

    private String cpf;

    @Column(name="name")

    private String name;

    @Column(name="lastname")

    private String lastName;

    @Column(name="age")

    private int age;

    @Column(name="sex")

    private String sex;

    @Column(name="phone")

    private String phone;

    @Column(name="address")

    private String address;

    @OneToOne(mappedBy = "student")
    // ALTER TABLE Enrollment ADD CONSTRAINT FK_studentId FOREIGN KEY (studentId) REFERENCES Student(id)
    private Enrollment enrollment;
}