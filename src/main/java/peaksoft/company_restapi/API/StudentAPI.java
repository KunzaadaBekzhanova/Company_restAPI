package peaksoft.company_restapi.API;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import peaksoft.company_restapi.exceptions.BadRequestException;
import peaksoft.company_restapi.exceptions.NotFoundException;
import peaksoft.company_restapi.models.Student;
import peaksoft.company_restapi.responses.Response;
import peaksoft.company_restapi.services.StudentService;

import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/company/group/student")
public class StudentAPI {
    private final StudentService studentService;

    @PostMapping("/save/{groupId}")
    public Response saveStudent(@PathVariable("groupId") Long groupId,
                                @RequestBody Student student){
        return studentService.saveStudent(student, groupId);
    }

    @GetMapping("/getStudent")
    public List<Student> getAllStudents(){
        return studentService.findAllStudents();
    }

    @GetMapping("/get/{studentId}")
    public Student getStudentById(@PathVariable("studentId") Long studentId){
        return studentService.getStudentById(studentId);
    }

    @PatchMapping("/update/{studentId}")
    public Response updateStudentById(@PathVariable("studentId") Long studentId,
                                      @RequestBody Student newStudent){
        return studentService.updateStudentById(studentId, newStudent);
    }

    @DeleteMapping("delete/{studentId}")
    public Response deleteStudentById(@PathVariable("studentId") Long studentId){
        return studentService.deleteStudentById(studentId);
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    public Response handleNotFoundException(NotFoundException notFoundException) {
        return Response.builder()
                .httpStatus(NOT_FOUND)
                .message(notFoundException.getMessage())
                .build();
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(BAD_REQUEST)
    public Response handleBadRequestException(BadRequestException badRequestException) {
        return Response.builder()
                .httpStatus(BAD_REQUEST)
                .message(badRequestException.getMessage())
                .build();
    }
}

