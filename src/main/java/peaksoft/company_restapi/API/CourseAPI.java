package peaksoft.company_restapi.API;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import peaksoft.company_restapi.DTO.CourseDTO;
import peaksoft.company_restapi.exceptions.BadRequestException;
import peaksoft.company_restapi.exceptions.NotFoundException;
import peaksoft.company_restapi.models.Course;
import peaksoft.company_restapi.responses.Response;
import peaksoft.company_restapi.services.CourseService;

import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/company/course")
public class CourseAPI {
    private final CourseService courseService;

    @GetMapping("/getCourse")
    public List<CourseDTO> getAllCourses(){
        return courseService.findAllCourses();
    }

    @GetMapping("/get/{courseId}")
    public CourseDTO getCourseById(@PathVariable("courseId") Long courseId){
        return courseService.getCourseById(courseId);
    }

    @PostMapping("/save/{companyId}")
    public Response saveCourse(@RequestBody CourseDTO course,
                               @PathVariable("companyId") Long companyId){
        return courseService.saveCourse(course, companyId);
    }

    @PatchMapping("/update/{id}")
    public Response updateCourse(@RequestBody Course course, @PathVariable("id") Long id){
        return courseService.updateCourseById(id, course);
    }

    @DeleteMapping("delete/{courseId}")
    public Response deleteCourseById(@PathVariable("courseId") Long courseId){
        return courseService.deleteCourseByID(courseId);
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(BAD_REQUEST)
    public Response handleBadRequestException(BadRequestException badRequestException, @PathVariable String id) {
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
