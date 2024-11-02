package com.unip.SistemaEducacional.dao;

import com.unip.SistemaEducacional.models.Enrollment;

import java.util.List;
import java.util.Optional;

public interface EnrollmentDao {
    List<Enrollment> getAllEnrollments();
    Optional<Enrollment> getEnrollmentByRa(String ra);
    Enrollment createEnrollment(Enrollment enrollment);
    Optional<Void> deleteEnrollmentByCpf(String cpf);
    Optional<Enrollment> changeStudentCourse(String ra, Integer courseCode);
    Optional<Enrollment> updateEnrollment(String ra, Enrollment enrollment);
    Optional<Void> deleteEnrollment(String ra);
}