package com.unip.SistemaEducacional.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "Course")
// CREATE TABLE Course (courseCode INT AUTO_INCREMENT PRIMARY KEY, courseName VARCHAR(255), courseLevel VARCHAR(255), duration VARCHAR(255), schedule VARCHAR(255), description VARCHAR(255))
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="courseCode")
    // CREATE COLUMN courseCode INT AUTO_INCREMENT PRIMARY KEY
    private Integer courseCode;

    @Column(name="courseName")
    // CREATE COLUMN courseName VARCHAR(255)
    private String courseName;

    @Column(name="courseLevel")
    // CREATE COLUMN courseLevel VARCHAR(255)
    private String courseLevel;

    @Column(name="duration")
    // CREATE COLUMN duration VARCHAR(255)
    private String duration;

    @Column(name="schedule")
    // CREATE COLUMN schedule VARCHAR(255)
    private String schedule;

    @Column(name="description")
    // CREATE COLUMN description VARCHAR(255)
    private String description;

    @OneToMany(mappedBy = "course")
    // ALTER TABLE Enrollment ADD CONSTRAINT FK_courseCode FOREIGN KEY (courseCode) REFERENCES Course(code)
    private List<Enrollment> enrollments;
}