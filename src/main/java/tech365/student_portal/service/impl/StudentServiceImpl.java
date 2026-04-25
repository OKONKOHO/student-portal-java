package tech365.student_portal.service.impl;

import tech365.student_portal.dto.request.StudentUpdateRequest;
import tech365.student_portal.dto.response.StudentResponse;
import tech365.student_portal.entity.Student;
import tech365.student_portal.entity.User;
import tech365.student_portal.exception.BadRequestException;
import tech365.student_portal.exception.ResourceNotFoundException;
import tech365.student_portal.repository.StudentRepository;
import tech365.student_portal.repository.UserRepository;
import tech365.student_portal.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final UserRepository userRepository;

    @Override
    public List<StudentResponse> getAllStudents() {
        return studentRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public StudentResponse getStudentById(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));
        return mapToResponse(student);
    }

    @Override
    public StudentResponse getMyProfile() {
        Student student = getAuthenticatedStudent();
        return mapToResponse(student);
    }

    @Override
    public StudentResponse updateMyProfile(StudentUpdateRequest request) {
        Student student = getAuthenticatedStudent();

        if (request.getMatricNumber() != null && !request.getMatricNumber().isBlank()) {
            boolean matricUsedByAnother = studentRepository.existsByMatricNumber(request.getMatricNumber())
                    && (student.getMatricNumber() == null || !student.getMatricNumber().equals(request.getMatricNumber()));

            if (matricUsedByAnother) {
                throw new BadRequestException("Matric number already exists");
            }

            student.setMatricNumber(request.getMatricNumber());
        }

        student.setDateOfBirth(request.getDateOfBirth());
        student.setGender(request.getGender());
        student.setAddress(request.getAddress());
        student.setDepartment(request.getDepartment());

        if (request.getEnrollmentDate() != null) {
            student.setEnrollmentDate(request.getEnrollmentDate());
        }

        student.setPassportPhotoUrl(request.getPassportPhotoUrl());
        student.setContactPhone(request.getContactPhone());
        student.setCourseOfStudy(request.getCourseOfStudy());

        if (request.getProgramStatus() != null) {
            student.setProgramStatus(request.getProgramStatus());
        }

        Student updatedStudent = studentRepository.save(student);
        return mapToResponse(updatedStudent);
    }

    @Override
    public void deleteStudent(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));
        studentRepository.delete(student);
    }

    private Student getAuthenticatedStudent() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Authenticated user not found"));

        return studentRepository.findByUserId(user.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Student profile not found"));
    }

    private StudentResponse mapToResponse(Student student) {
        return StudentResponse.builder()
                .id(student.getId())
                .userId(student.getUser().getId())
                .fullName(student.getUser().getFullName())
                .email(student.getUser().getEmail())
                .studentId(student.getStudentId())
                .matricNumber(student.getMatricNumber())
                .dateOfBirth(student.getDateOfBirth())
                .gender(student.getGender())
                .address(student.getAddress())
                .department(student.getDepartment())
                .enrollmentDate(student.getEnrollmentDate())
                .passportPhotoUrl(student.getPassportPhotoUrl())
                .contactPhone(student.getContactPhone())
                .courseOfStudy(student.getCourseOfStudy())
                .programStatus(student.getProgramStatus())
                .build();
    }
}