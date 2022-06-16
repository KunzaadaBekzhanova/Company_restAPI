package peaksoft.company_restapi.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourseDTO {
    private Long id;
    private String courseName;
    private String duration;
}
