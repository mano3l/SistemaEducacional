package com.unip.SistemaEducacional.controllers;
import com.unip.SistemaEducacional.repositories.*;

import com.azure.core.exception.ResourceNotFoundException;
import com.unip.SistemaEducacional.models.Course;
import com.unip.SistemaEducacional.models.Enrollment;
import com.unip.SistemaEducacional.models.Student;
import com.unip.SistemaEducacional.repositories.EnrollmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/enrollments")
public class EnrollmentController {

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @GetMapping
    public List<Enrollment> getAllEnrollments() {
        return enrollmentRepository.findAll();
    }

    @GetMapping("/{ra}")
    public ResponseEntity<Enrollment> getEnrollmentByRa(@PathVariable String ra) {
        return enrollmentRepository.findById(ra)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Enrollment createEnrollment(@RequestBody Enrollment enrollment) {
        return enrollmentRepository.save(enrollment);
    }

    @PutMapping("/{ra}")
    public ResponseEntity<Enrollment> updateEnrollment(@PathVariable String ra, @RequestBody Enrollment enrollment) {
        return enrollmentRepository.findById(ra)
                .map(existingEnrollment -> {
                    existingEnrollment.setEmail(enrollment.getEmail());
                    existingEnrollment.setDataMatricula(enrollment.getDataMatricula());
                    Enrollment updatedEnrollment = enrollmentRepository.save(existingEnrollment);
                    return ResponseEntity.ok(updatedEnrollment);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{ra}")
    public ResponseEntity<Object> deleteEnrollment(@PathVariable String ra) {
        return enrollmentRepository.findById(ra)
                .map(existingEnrollment -> {
                    enrollmentRepository.delete(existingEnrollment);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
