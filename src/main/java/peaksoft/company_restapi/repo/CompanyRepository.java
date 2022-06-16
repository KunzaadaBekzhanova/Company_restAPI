package peaksoft.company_restapi.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import peaksoft.company_restapi.models.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

    @Query("select case when count(s) > 0 then true else false end " +
            "from Company s where s.companyName = ?1 and s.locatedCity = ?2")
    boolean existsByCompanyName(String companyName, String locatedCity);
}