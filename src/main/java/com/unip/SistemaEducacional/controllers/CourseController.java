package com.unip.SistemaEducacional.controllers;

import com.unip.SistemaEducacional.models.Course;
import com.unip.SistemaEducacional.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CourseController {

    @Autowired
    private CourseRepository courseRepository;

    @GetMapping
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    @GetMapping("/{codigoPrograma}")
    public ResponseEntity<Course> getCourseByCodigo(@PathVariable Integer codigoPrograma) {
        return courseRepository.findById(codigoPrograma)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Course createCourse(@RequestBody Course course) {
        return courseRepository.save(course);
    }

    @PutMapping("/{codigoPrograma}")
    public ResponseEntity<Course> updateCourse(@PathVariable Integer codigoPrograma, @RequestBody Course course) {
        return courseRepository.findById(codigoPrograma)
                .map(existingCourse -> {
                    existingCourse.setNomeCurso(course.getNomeCurso());
                    existingCourse.setNivelDoCurso(course.getNivelDoCurso());
                    existingCourse.setDuracao(course.getDuracao());
                    existingCourse.setHorario(course.getHorario());
                    existingCourse.setDescricao(course.getDescricao());
                    Course updatedCourse = courseRepository.save(existingCourse);
                    return ResponseEntity.ok(updatedCourse);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("/{codigoPrograma}/name")
    public ResponseEntity<Course> changeCourseName(@PathVariable Integer codigoPrograma, @RequestBody String newName) {
        return courseRepository.findById(codigoPrograma)
                .map(existingCourse -> {
                    existingCourse.setNomeCurso(newName);
                    Course updatedCourse = courseRepository.save(existingCourse);
                    return ResponseEntity.ok(updatedCourse);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("/{codigoPrograma}/level")
    public ResponseEntity<Course> changeCourseLevel(@PathVariable Integer codigoPrograma, @RequestBody String newLevel) {
        return courseRepository.findById(codigoPrograma)
                .map(existingCourse -> {
                    existingCourse.setNivelDoCurso(newLevel);
                    Course updatedCourse = courseRepository.save(existingCourse);
                    return ResponseEntity.ok(updatedCourse);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("/{codigoPrograma}/duration")
    public ResponseEntity<Course> changeCourseDuration(@PathVariable Integer codigoPrograma, @RequestBody String newDuration) {
        return courseRepository.findById(codigoPrograma)
                .map(existingCourse -> {
                    existingCourse.setDuracao(newDuration);
                    Course updatedCourse = courseRepository.save(existingCourse);
                    return ResponseEntity.ok(updatedCourse);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("/{codigoPrograma}/description")
    public ResponseEntity<Course> changeCourseDescription(@PathVariable Integer codigoPrograma, @RequestBody String newDescription) {
        return courseRepository.findById(codigoPrograma)
                .map(existingCourse -> {
                    existingCourse.setDescricao(newDescription);
                    Course updatedCourse = courseRepository.save(existingCourse);
                    return ResponseEntity.ok(updatedCourse);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{codigoPrograma}")
    public ResponseEntity<Object> deleteCourse(@PathVariable Integer codigoPrograma) {
        return courseRepository.findById(codigoPrograma)
                .map(existingCourse -> {
                    courseRepository.delete(existingCourse);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }
}
