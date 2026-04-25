package tech365.student_portal.service;

import java.util.List;

import tech365.student_portal.dto.request.StudentUpdateRequest;
import tech365.student_portal.dto.response.StudentResponse;

public interface StudentService {
    List<StudentResponse> getAllStudents();
    StudentResponse getStudentById(Long id);
    StudentResponse getMyProfile();
    StudentResponse updateMyProfile(StudentUpdateRequest request);
    void deleteStudent(Long id);
}

