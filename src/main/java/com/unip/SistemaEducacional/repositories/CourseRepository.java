package com.unip.SistemaEducacional.repositories;

import com.unip.SistemaEducacional.models.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Integer> {
}