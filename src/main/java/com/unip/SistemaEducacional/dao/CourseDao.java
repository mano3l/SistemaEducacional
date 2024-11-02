package com.unip.SistemaEducacional.dao;

import com.unip.SistemaEducacional.models.Course;

import java.util.List;
import java.util.Optional;

public interface CourseDao {
    List<Course> getAllCourses();
    Optional<Course> getCourseByCode(Integer courseCode);
    Course createCourse(Course course);
    Optional<Course> updateCourse(Integer courseCode, Course course);
    Optional<Course> changeCourseName(Integer courseCode, String newName);
    Optional<Course> changeCourseLevel(Integer courseCode, String newLevel);
    Optional<Course> changeCourseDuration(Integer courseCode, String newDuration);
    Optional<Course> changeCourseDescription(Integer courseCode, String newDescription);
    Optional<Void> deleteCourse(Integer courseCode);
}