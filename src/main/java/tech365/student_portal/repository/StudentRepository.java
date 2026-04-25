package tech365.student_portal.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import tech365.student_portal.entity.Student;

import java.time.LocalDate;


public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findByUserId(Long userId);
    boolean existsByStudentId(String studentId);
    boolean existsByMatricNumber(String matricNumber);
    long countByEnrollmentDate(LocalDate enrollmentDate);
}
