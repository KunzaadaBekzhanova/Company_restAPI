package peaksoft.company_restapi.API;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import peaksoft.company_restapi.DTO.TeacherDTO;
import peaksoft.company_restapi.exceptions.BadRequestException;
import peaksoft.company_restapi.exceptions.NotFoundException;
import peaksoft.company_restapi.models.Teacher;
import peaksoft.company_restapi.responses.Response;
import peaksoft.company_restapi.services.TeacherService;

import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/company/teacher")
public class TeacherAPI {

    private final TeacherService teacherService;

    @PostMapping("/save/{courseId}")
    public Response saveTeacher(@RequestBody TeacherDTO teacher,
                                @PathVariable("courseId") Long courseId){
        return teacherService.saveTeacher(courseId, teacher);
    }

    @GetMapping("/get/{teacherId}")
    public TeacherDTO getTeacherById(@PathVariable("teacherId") Long teacherId){
        return teacherService.getTeacherById(teacherId);
    }

    @GetMapping("/getTeacher")
    public List<TeacherDTO> getAllTeachers(){
        return teacherService.findAllTeacher();
    }

    @PatchMapping("/update/{teacherId}")
    public Response updateTeacherByID(@PathVariable("teacherId") Long teacherId,
                                      @RequestBody Teacher teacher){
        return teacherService.updateTeacherById(teacherId, teacher);
    }

    @DeleteMapping("/delete/{id}")
    public Response delete(@PathVariable("id") Long id) {
        return teacherService.deleteTeacherById(id);
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
