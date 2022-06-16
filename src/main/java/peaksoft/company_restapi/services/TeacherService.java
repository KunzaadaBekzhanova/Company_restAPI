package peaksoft.company_restapi.services;

import peaksoft.company_restapi.DTO.TeacherDTO;
import peaksoft.company_restapi.models.Course;
import peaksoft.company_restapi.models.Teacher;
import peaksoft.company_restapi.responses.Response;

import javax.transaction.Transactional;
import java.util.List;

public interface TeacherService {

    Response saveTeacher(Long courseId, TeacherDTO teacher);

    void doesTeacherExist(Course course);

    List<TeacherDTO> findAllTeacher();

    TeacherDTO getTeacherById (Long id);

    @Transactional
    Response updateTeacherById(Long id, Teacher newTeacher);

    Response deleteTeacherById(Long id);

}
