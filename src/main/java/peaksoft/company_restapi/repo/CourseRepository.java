package peaksoft.company_restapi.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import peaksoft.company_restapi.models.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {

    @Query("select case when count(s) > 0 then true else false end " +
            "from Course s where s.courseName = ?1")
    boolean existsByCourseName(String courseName);
}