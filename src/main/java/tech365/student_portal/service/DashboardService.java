package tech365.student_portal.service;

import tech365.student_portal.dto.response.AdminDashboardResponse;
import tech365.student_portal.dto.response.StudentDashboardResponse;

public interface DashboardService {
    StudentDashboardResponse getStudentDashboard();
    AdminDashboardResponse getAdminDashboard();
}

