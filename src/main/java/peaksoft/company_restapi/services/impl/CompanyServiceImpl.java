package peaksoft.company_restapi.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import peaksoft.company_restapi.DTO.CompanyDTO;
import peaksoft.company_restapi.exceptions.BadRequestException;
import peaksoft.company_restapi.exceptions.NotFoundException;
import peaksoft.company_restapi.mappers.CompanyMapper;
import peaksoft.company_restapi.models.Company;
import peaksoft.company_restapi.repo.CompanyRepository;
import peaksoft.company_restapi.responses.Response;
import peaksoft.company_restapi.services.CompanyService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.HttpStatus.RESET_CONTENT;

@Service
@RequiredArgsConstructor
@Slf4j
public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepository companyRepository;
    private final CompanyMapper companyMapper;

    @Override
    public Response saveCompany(CompanyDTO companyDTO){
        doesCompanyExist(companyDTO);
        companyRepository.save(companyMapper.dtoToEntity(companyDTO));
        return Response.builder()
                .httpStatus(HttpStatus.CREATED)
                .message(String.format("Company with name: %s, and city: %s successfully created ", companyDTO.getCompanyName(),
                        companyDTO.getLocatedCity()))
                .build();
    }
    @Override
    public void doesCompanyExist(CompanyDTO company){
        if(companyRepository.existsByCompanyName(company.getCompanyName(), company.getLocatedCity())){
            log.warn("company with companyName: {} and locatedCity: {} -> already exists",
                    company.getCompanyName(),company.getLocatedCity());
            throw new BadRequestException(
                    "company with companyName: " + company.getCompanyName() +
                            " and locatedCity: " + company.getLocatedCity()+ " -> already exists"
            );
        }
    }

    @Override
    public List<CompanyDTO> findAllCompanies() {
        return companyMapper.entityListToDtoList(companyRepository.findAll());
    }

    @Override
    public CompanyDTO getCompanyById(Long id) {
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Company with id = {} does not exists", id);
                    throw new NotFoundException(
                            String.format("Company with id = %s does not exists", id)
                    );
                });
        log.info("Founded Company with id = {}", id);
        return companyMapper.entityToDto(company);
    }

    @Override
    public Response deleteCompanyById(Long id) {
        try {
            companyRepository.deleteById(id);
        }
        catch(Exception e){
            throw new NotFoundException(String.format("Company with id %s has not been found!", id));
        }
        log.info("Company with id = {} has successfully deleted", id);
        String message = String.format("Company with id = %s has successfully deleted", id);
        return Response.builder()
                .httpStatus(OK)
                .message(message)
                .build();
    }

    @Override
    @Transactional
    public Response updateCompanyById(Long id, Company newCompany) {
        Company company = companyRepository.getById(id);

        String companyName = company.getCompanyName();
        String newCompanyName = newCompany.getCompanyName();
        if (!Objects.equals(companyName, newCompanyName)) {
            company.setCompanyName(newCompanyName);
            log.info("Company with id = {} changed name from {} to {}",
                    id, companyName, newCompanyName);
        }

        String locatedCity = company.getLocatedCity();
        String newLocatedCity = newCompany.getLocatedCity();
        if (!Objects.equals(locatedCity, newLocatedCity)) {
            company.setLocatedCity(newLocatedCity);
            log.info("Company with id = {} changed name from {} to {}",
                    id, locatedCity, newLocatedCity);
        }

        String message = String.format("Company with companyId = %s has successfully updated", id);
        return Response.builder()
                .httpStatus(RESET_CONTENT)
                .message(message)
                .build();
    }


}
