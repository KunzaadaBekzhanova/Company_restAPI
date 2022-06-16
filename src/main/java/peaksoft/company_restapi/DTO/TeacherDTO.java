package peaksoft.company_restapi.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TeacherDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
}
