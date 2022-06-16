package peaksoft.company_restapi.API;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import peaksoft.company_restapi.DTO.CompanyDTO;
import peaksoft.company_restapi.exceptions.BadRequestException;
import peaksoft.company_restapi.exceptions.NotFoundException;
import peaksoft.company_restapi.models.Company;
import peaksoft.company_restapi.responses.Response;
import peaksoft.company_restapi.services.CompanyService;

import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/company")
public class CompanyAPI {

    private final CompanyService service;

    @GetMapping("/getCompany")
    public List<CompanyDTO> getAllCompanies(){
        return service.findAllCompanies();
    }

    @GetMapping("/get/{id}")
    public CompanyDTO getCompanyById(@PathVariable("id") Long id) {
        return (service.getCompanyById(id));
    }

    @PostMapping("/save")
    public Response saveCompany(@RequestBody CompanyDTO companyDTO){
        return service.saveCompany(companyDTO);
    }

    @PatchMapping("/update/{id}")
    public Response updateCompany(@RequestBody Company company, @PathVariable("id") Long id){
        return service.updateCompanyById(id, company);
    }

    @DeleteMapping("/delete/{id}")
    public Response delete(@PathVariable("id") Long id) {
        return service.deleteCompanyById(id);
    }



    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(BAD_REQUEST)
    public Response handleBadRequestException(BadRequestException badRequestException) {
        return Response.builder()
                .httpStatus(BAD_REQUEST)
                .message(badRequestException.getMessage())
                .build();
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    public Response handleNotFoundException(NotFoundException notFoundException) {
        return Response.builder()
                .httpStatus(NOT_FOUND)
                .message(notFoundException.getMessage())
                .build();
    }

}
