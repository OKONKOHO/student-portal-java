package tech365.student_portal.dto.response;

import tech365.student_portal.enums.Gender;
import tech365.student_portal.enums.ProgramStatus;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentResponse {

    private Long id;
    private Long userId;
    private String fullName;
    private String email;
    private String studentId;
    private String matricNumber;
    private LocalDate dateOfBirth;
    private Gender gender;
    private String address;
    private String department;
    private LocalDate enrollmentDate;
    private String passportPhotoUrl;
    private String contactPhone;
    private String courseOfStudy;
    private ProgramStatus programStatus;
}
