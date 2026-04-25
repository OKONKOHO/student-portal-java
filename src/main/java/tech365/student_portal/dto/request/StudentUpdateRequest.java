package tech365.student_portal.dto.request;

import tech365.student_portal.enums.Gender;
import tech365.student_portal.enums.ProgramStatus;
import jakarta.validation.constraints.Past;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentUpdateRequest {

    private String matricNumber;

    @Past(message = "Date of birth must be in the past")
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