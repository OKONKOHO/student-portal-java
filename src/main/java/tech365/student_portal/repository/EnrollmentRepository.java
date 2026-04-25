package tech365.student_portal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import tech365.student_portal.entity.Enrollment;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    List<Enrollment> findByStudentId(Long studentId);
    boolean existsByStudentIdAndCourseId(Long studentId, Long courseId);
    long countByStudentId(Long studentId);
}

