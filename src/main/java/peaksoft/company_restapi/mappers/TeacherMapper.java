package peaksoft.company_restapi.mappers;

import org.mapstruct.Mapper;
import peaksoft.company_restapi.DTO.TeacherDTO;
import peaksoft.company_restapi.models.Teacher;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TeacherMapper {
    //Converting dto to entity
    Teacher dtoToEntity(TeacherDTO dto);

    //Converting entity to dto
    TeacherDTO entityToDto(Teacher teacher);

    //Converting list to dto list
    List<TeacherDTO> entityListToDtoList(List<Teacher> teachers);

    List<Teacher> dtoListToEntityList(List<TeacherDTO> dtoTeachers);
}
