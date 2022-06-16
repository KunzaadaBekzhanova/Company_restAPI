package peaksoft.company_restapi.DTO;

import lombok.Getter;
import lombok.Setter;
import peaksoft.company_restapi.models.enums.StudyFormat;

@Getter
@Setter
public class StudentDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private StudyFormat studyFormat;
}
