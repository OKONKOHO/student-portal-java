package tech365.student_portal.dto.response;

import tech365.student_portal.enums.CourseStatus;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseResponse {

    private Long id;
    private String title;
    private String code;
    private String description;
    private String semester;
    private Integer creditUnit;
    private CourseStatus status;
}