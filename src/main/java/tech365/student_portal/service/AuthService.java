package tech365.student_portal.service;

import tech365.student_portal.dto.request.LoginRequest;
import tech365.student_portal.dto.request.SignupRequest;
import tech365.student_portal.dto.response.AuthResponse;
import tech365.student_portal.dto.response.CurrentUserResponse;

public interface AuthService {
    AuthResponse signup(SignupRequest request);
    AuthResponse login(LoginRequest request);
    CurrentUserResponse getCurrentUser();
}