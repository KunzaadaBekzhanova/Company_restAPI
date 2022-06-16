package peaksoft.company_restapi.services;

import peaksoft.company_restapi.DTO.CourseDTO;
import peaksoft.company_restapi.models.Course;
import peaksoft.company_restapi.responses.Response;

import javax.transaction.Transactional;
import java.util.List;

public interface CourseService {

    Response saveCourse(CourseDTO course, Long companyId);

    void doesCourseExist(CourseDTO courseDTO);

    List<CourseDTO> findAllCourses();

    CourseDTO getCourseById (Long id);

    @Transactional
    Response updateCourseById(Long id, Course newCourse);

    Response deleteCourseByID(Long id);

}