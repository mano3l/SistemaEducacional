package com.unip.SistemaEducacional.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "Enrollment")
// CREATE TABLE Enrollment (ra VARCHAR(255) PRIMARY KEY, studentId INT NOT NULL, email VARCHAR(255), courseCode INT NOT NULL, enrollmentDate DATE)
public class Enrollment {
    @Id
    @Column(name="ra")
    // CREATE COLUMN ra VARCHAR(255) PRIMARY KEY
    private String ra;

    @OneToOne
    @JoinColumn(name = "studentId", referencedColumnName = "id", nullable = false)
    @JsonIgnore
    // CREATE COLUMN studentId INT NOT NULL
    // ALTER TABLE Enrollment ADD CONSTRAINT FK_studentId FOREIGN KEY (studentId) REFERENCES Student(id)
    private Student student;

    @Column(name="email")
    // CREATE COLUMN email VARCHAR(255)
    private String email;

    @ManyToOne
    @JoinColumn(name = "courseCode", referencedColumnName = "code", nullable = false)
    @JsonIgnore
    // CREATE COLUMN courseCode INT NOT NULL
    // ALTER TABLE Enrollment ADD CONSTRAINT FK_courseCode FOREIGN KEY (courseCode) REFERENCES Course(code)
    private Course course;

    @Column(name="enrollmentDate")
    // CREATE COLUMN enrollmentDate DATE
    private LocalDate enrollmentDate;
}