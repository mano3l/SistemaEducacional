package com.unip.SistemaEducacional.repositories;

import com.unip.SistemaEducacional.models.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EnrollmentRepository extends JpaRepository<Enrollment, String> {
    Enrollment findByStudent_Cpf(String cpf);

    List<Enrollment> findByCourseCode(Integer courseCode);
    List<Enrollment> findByStudentId(Integer studentId);
}

