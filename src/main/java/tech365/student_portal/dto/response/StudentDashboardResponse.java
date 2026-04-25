package tech365.student_portal.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentDashboardResponse {
    private String welcomeMessage;
    private long totalEnrolledCourses;
}
