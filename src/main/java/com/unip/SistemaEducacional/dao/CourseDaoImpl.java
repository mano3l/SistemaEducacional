package com.unip.SistemaEducacional.dao;

import com.unip.SistemaEducacional.models.Course;
import com.unip.SistemaEducacional.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseDaoImpl {

    private final CourseRepository courseRepository;

    @Autowired
    public CourseDaoImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    // SELECT * FROM Course
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    // SELECT * FROM Course WHERE code = ?
    public Optional<Course> getCourseByCode(Integer courseCode) {
        return courseRepository.findById(courseCode);
    }

    // INSERT INTO Course (courseName, courseLevel, duration, schedule, description) VALUES (?, ?, ?, ?, ?)
    public Course createCourse(Course course) {
        return courseRepository.save(course);
    }

    // UPDATE Course SET courseName = ?, courseLevel = ?, duration = ?, schedule = ?, description = ? WHERE code = ?
    public Optional<Course> updateCourse(Integer courseCode, Course course) {
        return courseRepository.findById(courseCode)
                .map(existingCourse -> {
                    existingCourse.setCourseName(course.getCourseName());
                    existingCourse.setCourseLevel(course.getCourseLevel());
                    existingCourse.setDuration(course.getDuration());
                    existingCourse.setSchedule(course.getSchedule());
                    existingCourse.setDescription(course.getDescription());
                    return courseRepository.save(existingCourse);
                });
    }

    // UPDATE Course SET courseName = ? WHERE code = ?
    public Optional<Course> changeCourseName(Integer courseCode, String newName) {
        return courseRepository.findById(courseCode)
                .map(existingCourse -> {
                    existingCourse.setCourseName(newName);
                    return courseRepository.save(existingCourse);
                });
    }

    // UPDATE Course SET courseLevel = ? WHERE code = ?
    public Optional<Course> changeCourseLevel(Integer courseCode, String newLevel) {
        return courseRepository.findById(courseCode)
                .map(existingCourse -> {
                    existingCourse.setCourseLevel(newLevel);
                    return courseRepository.save(existingCourse);
                });
    }

    // UPDATE Course SET duration = ? WHERE code = ?
    public Optional<Course> changeCourseDuration(Integer courseCode, String newDuration) {
        return courseRepository.findById(courseCode)
                .map(existingCourse -> {
                    existingCourse.setDuration(newDuration);
                    return courseRepository.save(existingCourse);
                });
    }

    // UPDATE Course SET description = ? WHERE code = ?
    public Optional<Course> changeCourseDescription(Integer courseCode, String newDescription) {
        return courseRepository.findById(courseCode)
                .map(existingCourse -> {
                    existingCourse.setDescription(newDescription);
                    return courseRepository.save(existingCourse);
                });
    }

    // DELETE FROM Course WHERE code = ?
    public Optional<Void> deleteCourse(Integer courseCode) {
        return courseRepository.findById(courseCode)
                .map(existingCourse -> {
                    courseRepository.delete(existingCourse);
                    return null;
                });
    }
}