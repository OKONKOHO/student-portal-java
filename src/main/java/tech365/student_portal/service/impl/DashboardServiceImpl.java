package tech365.student_portal.service.impl;

import tech365.student_portal.dto.response.AdminDashboardResponse;
import tech365.student_portal.dto.response.StudentDashboardResponse;
import tech365.student_portal.entity.Student;
import tech365.student_portal.entity.User;
import tech365.student_portal.enums.CourseStatus;
import tech365.student_portal.exception.ResourceNotFoundException;
import tech365.student_portal.repository.CourseRepository;
import tech365.student_portal.repository.EnrollmentRepository;
import tech365.student_portal.repository.StudentRepository;
import tech365.student_portal.repository.UserRepository;
import tech365.student_portal.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class DashboardServiceImpl implements DashboardService {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final EnrollmentRepository enrollmentRepository;
    private final UserRepository userRepository;

    @Override
    public StudentDashboardResponse getStudentDashboard() {
        Student student = getAuthenticatedStudent();
        long totalEnrolledCourses = enrollmentRepository.countByStudentId(student.getId());

        return StudentDashboardResponse.builder()
                .welcomeMessage("Welcome back, " + student.getUser().getFullName())
                .totalEnrolledCourses(totalEnrolledCourses)
                .build();
    }

    @Override
    public AdminDashboardResponse getAdminDashboard() {
        long totalStudents = studentRepository.count();
        long activeCourses = courseRepository.countByStatus(CourseStatus.ACTIVE);
        long recentRegistrations = studentRepository.countByEnrollmentDate(LocalDate.now());

        return AdminDashboardResponse.builder()
                .totalStudents(totalStudents)
                .activeCourses(activeCourses)
                .recentRegistrations(recentRegistrations)
                .build();
    }

    private Student getAuthenticatedStudent() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Authenticated user not found"));

        return studentRepository.findByUserId(user.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Student profile not found"));
    }
}

