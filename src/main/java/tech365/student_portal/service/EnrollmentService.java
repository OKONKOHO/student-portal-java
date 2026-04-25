package tech365.student_portal.service;

import java.util.List;
import java.util.Optional;

import tech365.student_portal.entity.Enrollment;

public interface EnrollmentService {
    List<Enrollment> getAllEnrollments();
    Optional<Enrollment> getEnrollmentById(Long id);
    Enrollment saveEnrollment(Enrollment enrollment);
    void deleteEnrollment(Long id);
}