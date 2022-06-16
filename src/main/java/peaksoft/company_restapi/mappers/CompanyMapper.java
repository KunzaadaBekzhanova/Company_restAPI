package peaksoft.company_restapi.mappers;

import org.mapstruct.Mapper;
import peaksoft.company_restapi.DTO.CompanyDTO;
import peaksoft.company_restapi.models.Company;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CompanyMapper {

    //Converting dto to entity
    Company dtoToEntity(CompanyDTO dto);

    //Converting entity to dto
    CompanyDTO entityToDto(Company company);

    //Converting list to dto list
    List<CompanyDTO> entityListToDtoList(List<Company> companies);

    List<Company> dtoListToEntityList(List<CompanyDTO> dtoCompanies);


}
