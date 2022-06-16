package peaksoft.company_restapi.DTO;

import lombok.Data;

@Data
public class CompanyDTO {
    private Long id;
    private String companyName;
    private String locatedCity;
}