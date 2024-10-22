package com.unip.SistemaEducacional.services;

import com.unip.SistemaEducacional.models.Course;
import com.unip.SistemaEducacional.models.Enrollment;
import com.unip.SistemaEducacional.repositories.CourseRepository;
import com.unip.SistemaEducacional.repositories.EnrollmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final CourseRepository courseRepository;

    @Autowired
    public EnrollmentService(EnrollmentRepository enrollmentRepository, CourseRepository courseRepository) {
        this.enrollmentRepository = enrollmentRepository;
        this.courseRepository = courseRepository;
    }

    public List<Enrollment> getAllEnrollments() {
        return enrollmentRepository.findAll();
    }

    public Optional<Enrollment> getEnrollmentByRa(String ra) {
        return enrollmentRepository.findById(ra);
    }

    public Enrollment createEnrollment(Enrollment enrollment) {
        return enrollmentRepository.save(enrollment);
    }

    public Optional<Void> deleteEnrollmentByCpf(String cpf) {
        Enrollment enrollment = enrollmentRepository.findByStudent_Cpf(cpf);
        return Optional.ofNullable(enrollment)
                .map(existingEnrollment -> {
                    enrollmentRepository.delete(existingEnrollment);
                    return null;
                });
    }

    public Optional<Enrollment> changeStudentCourse(String ra, Integer codigoCurso) {
        return enrollmentRepository.findById(ra)
                .map(existingEnrollment -> {
                    Course newCourse = courseRepository.findById(codigoCurso)
                            .orElseThrow(() -> new NoSuchElementException("Course not found"));
                    existingEnrollment.setCourse(newCourse);
                    return enrollmentRepository.save(existingEnrollment);
                });
    }

    public Optional<Enrollment> updateEnrollment(String ra, Enrollment enrollment) {
        return enrollmentRepository.findById(ra)
                .map(existingEnrollment -> {
                    existingEnrollment.setEmail(enrollment.getEmail());
                    existingEnrollment.setDataMatricula(enrollment.getDataMatricula());
                    return enrollmentRepository.save(existingEnrollment);
                });
    }

    public Optional<Void> deleteEnrollment(String ra) {
        return enrollmentRepository.findById(ra)
                .map(existingEnrollment -> {
                    enrollmentRepository.delete(existingEnrollment);
                    return null;
                });
    }
}