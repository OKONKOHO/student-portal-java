package tech365.student_portal.service;

import java.util.List;
import java.util.Optional;

import tech365.student_portal.entity.Course;

public interface CourseService {
    List<Course> getAllCourses();
    Optional<Course> getCourseById(Long id);
    Course saveCourse(Course course);
    void deleteCourse(Long id);
}