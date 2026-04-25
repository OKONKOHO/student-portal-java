package tech365.student_portal.dto.request;

import tech365.student_portal.enums.CourseStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseRequest {

    @NotBlank(message = "Course title is required")
    private String title;

    @NotBlank(message = "Course code is required")
    private String code;

    private String description;
    private String semester;

    @NotNull(message = "Credit unit is required")
    private Integer creditUnit;

    @NotNull(message = "Course status is required")
    private CourseStatus status;
}