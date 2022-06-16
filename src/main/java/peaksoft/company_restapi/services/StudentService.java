package peaksoft.company_restapi.services;

import peaksoft.company_restapi.models.Student;
import peaksoft.company_restapi.responses.Response;

import javax.transaction.Transactional;
import java.util.List;

public interface StudentService {
    Response saveStudent(Student student, Long groupId);


    void doesStudentExist(String studentEmail);

    List<Student> findAllStudents();

    Student getStudentById (Long id);

    @Transactional
    Response updateStudentById(Long id, Student newStudent);

    Response deleteStudentById(Long id);
}
