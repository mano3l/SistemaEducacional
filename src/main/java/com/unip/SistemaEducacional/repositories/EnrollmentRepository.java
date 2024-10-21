package com.unip.SistemaEducacional.repositories;

import com.unip.SistemaEducacional.models.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnrollmentRepository extends JpaRepository<Enrollment, String> {
}

