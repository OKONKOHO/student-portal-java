package tech365.student_portal.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CurrentUserResponse {
    private Long userId;
    private String fullName;
    private String email;
    private String role;
}