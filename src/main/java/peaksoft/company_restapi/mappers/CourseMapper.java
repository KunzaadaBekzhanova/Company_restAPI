package peaksoft.company_restapi.mappers;

import org.mapstruct.Mapper;
import peaksoft.company_restapi.DTO.CourseDTO;
import peaksoft.company_restapi.models.Course;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CourseMapper {
    //Converting dto to entity
    Course dtoToEntity(CourseDTO dto);

    //Converting entity to dto
    CourseDTO entityToDto(Course course);

    //Converting list to dto list
    List<CourseDTO> entityListToDtoList(List<Course> courses);

    List<Course> dtoListToEntityList(List<CourseDTO> dtoCourses);
}
