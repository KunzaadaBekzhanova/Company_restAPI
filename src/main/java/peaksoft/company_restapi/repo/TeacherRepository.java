package peaksoft.company_restapi.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import peaksoft.company_restapi.models.Course;
import peaksoft.company_restapi.models.Teacher;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {

    @Query("select case when count(s) > 0 then true else false end " +
            "from Teacher s where s.course = ?1")
    boolean existsByCountOfTeachersInCourse(Course course);

}