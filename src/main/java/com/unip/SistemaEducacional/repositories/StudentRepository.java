package com.unip.SistemaEducacional.repositories;

import com.unip.SistemaEducacional.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {
    // You can define custom query methods here if needed
}
