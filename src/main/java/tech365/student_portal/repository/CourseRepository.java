package tech365.student_portal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tech365.student_portal.entity.Course;
import tech365.student_portal.enums.CourseStatus;


public interface CourseRepository extends JpaRepository<Course, Long> {
    boolean existsByCode(String code);
    long countByStatus(CourseStatus status);
}
