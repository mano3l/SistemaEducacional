package com.unip.SistemaEducacional.repositories;

import com.unip.SistemaEducacional.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Integer> {
    Optional<Student> findByCpf(String cpf);
}
