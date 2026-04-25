package tech365.student_portal.dto.response;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdminDashboardResponse {
    private long totalStudents;
    private long activeCourses;
    private long recentRegistrations;
}

