package peaksoft.company_restapi.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import peaksoft.company_restapi.models.Group;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {
    @Query("select case when count(s) > 0 then true else false end " +
            "from Group s where s.groupName = ?1")
    boolean existsByGroupName(String groupName);
}
