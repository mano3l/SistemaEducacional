package com.unip.SistemaEducacional.services;

import com.unip.SistemaEducacional.models.Course;
import com.unip.SistemaEducacional.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Optional<Course> getCourseByCodigo(Integer codigoPrograma) {
        return courseRepository.findById(codigoPrograma);
    }

    public Course createCourse(Course course) {
        return courseRepository.save(course);
    }

    public Optional<Course> updateCourse(Integer codigoPrograma, Course course) {
        return courseRepository.findById(codigoPrograma)
                .map(existingCourse -> {
                    existingCourse.setNomeCurso(course.getNomeCurso());
                    existingCourse.setNivelDoCurso(course.getNivelDoCurso());
                    existingCourse.setDuracao(course.getDuracao());
                    existingCourse.setHorario(course.getHorario());
                    existingCourse.setDescricao(course.getDescricao());
                    return courseRepository.save(existingCourse);
                });
    }

    public Optional<Course> changeCourseName(Integer codigoPrograma, String newName) {
        return courseRepository.findById(codigoPrograma)
                .map(existingCourse -> {
                    existingCourse.setNomeCurso(newName);
                    return courseRepository.save(existingCourse);
                });
    }

    public Optional<Course> changeCourseLevel(Integer codigoPrograma, String newLevel) {
        return courseRepository.findById(codigoPrograma)
                .map(existingCourse -> {
                    existingCourse.setNivelDoCurso(newLevel);
                    return courseRepository.save(existingCourse);
                });
    }

    public Optional<Course> changeCourseDuration(Integer codigoPrograma, String newDuration) {
        return courseRepository.findById(codigoPrograma)
                .map(existingCourse -> {
                    existingCourse.setDuracao(newDuration);
                    return courseRepository.save(existingCourse);
                });
    }

    public Optional<Course> changeCourseDescription(Integer codigoPrograma, String newDescription) {
        return courseRepository.findById(codigoPrograma)
                .map(existingCourse -> {
                    existingCourse.setDescricao(newDescription);
                    return courseRepository.save(existingCourse);
                });
    }

    public Optional<Void> deleteCourse(Integer codigoPrograma) {
        return courseRepository.findById(codigoPrograma)
                .map(existingCourse -> {
                    courseRepository.delete(existingCourse);
                    return null;
                });
    }
}