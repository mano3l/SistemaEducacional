package com.unip.SistemaEducacional.dao;

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
public class EnrollmentDaoImpl {

    private final EnrollmentRepository enrollmentRepository;
    private final CourseRepository courseRepository;

    @Autowired
    public EnrollmentDaoImpl(EnrollmentRepository enrollmentRepository, CourseRepository courseRepository) {
        this.enrollmentRepository = enrollmentRepository;
        this.courseRepository = courseRepository;
    }

    // SELECT * FROM Enrollment
    public List<Enrollment> getAllEnrollments() {
        return enrollmentRepository.findAll();
    }

    // SELECT * FROM Enrollment WHERE ra = ?
    public Optional<Enrollment> getEnrollmentByRa(String ra) {
        return enrollmentRepository.findById(ra);
    }

    // INSERT INTO Enrollment (ra, studentId, email, courseCode, enrollmentDate) VALUES (?, ?, ?, ?, ?)
    public Enrollment createEnrollment(Enrollment enrollment) {
        return enrollmentRepository.save(enrollment);
    }

    // DELETE FROM Enrollment WHERE student.cpf = ?
    public Optional<Void> deleteEnrollmentByCpf(String cpf) {
        Enrollment enrollment = enrollmentRepository.findByStudent_Cpf(cpf);
        return Optional.ofNullable(enrollment)
                .map(existingEnrollment -> {
                    enrollmentRepository.delete(existingEnrollment);
                    return null;
                });
    }

    // UPDATE Enrollment SET courseCode = ? WHERE ra = ?
    public Optional<Enrollment> changeStudentCourse(String ra, Integer courseCode) {
        return enrollmentRepository.findById(ra)
                .map(existingEnrollment -> {
                    Course newCourse = courseRepository.findById(courseCode)
                            .orElseThrow(() -> new NoSuchElementException("Course not found"));
                    existingEnrollment.setCourse(newCourse);
                    return enrollmentRepository.save(existingEnrollment);
                });
    }

    // UPDATE Enrollment SET email = ?, enrollmentDate = ? WHERE ra = ?
    public Optional<Enrollment> updateEnrollment(String ra, Enrollment enrollment) {
        return enrollmentRepository.findById(ra)
                .map(existingEnrollment -> {
                    existingEnrollment.setEmail(enrollment.getEmail());
                    existingEnrollment.setEnrollmentDate(enrollment.getEnrollmentDate());
                    return enrollmentRepository.save(existingEnrollment);
                });
    }

    // DELETE FROM Enrollment WHERE ra = ?
    public Optional<Void> deleteEnrollment(String ra) {
        return enrollmentRepository.findById(ra)
                .map(existingEnrollment -> {
                    enrollmentRepository.delete(existingEnrollment);
                    return null;
                });
    }
}