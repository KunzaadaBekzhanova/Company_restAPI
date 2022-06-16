package peaksoft.company_restapi.services;

import peaksoft.company_restapi.DTO.CompanyDTO;
import peaksoft.company_restapi.models.Company;
import peaksoft.company_restapi.responses.Response;

import javax.transaction.Transactional;
import java.util.List;

public interface CompanyService {
    Response saveCompany(CompanyDTO companyDTO);

    void doesCompanyExist(CompanyDTO companyDTO);



    List<CompanyDTO> findAllCompanies();

    CompanyDTO getCompanyById (Long id);

    @Transactional
    Response updateCompanyById(Long id, Company newCompany);

    Response deleteCompanyById(Long id);


}