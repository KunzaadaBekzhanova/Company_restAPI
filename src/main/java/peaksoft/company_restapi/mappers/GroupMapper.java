package peaksoft.company_restapi.mappers;

import org.mapstruct.Mapper;
import peaksoft.company_restapi.DTO.GroupDTO;
import peaksoft.company_restapi.models.Group;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GroupMapper {
    //Converting dto to entity
    Group dtoToEntity(GroupDTO dto);

    //Converting entity to dto
    GroupDTO entityToDto(Group group);

    //Converting list to dto list
    List<GroupDTO> entityListToDtoList(List<Group> groups);

    List<Group> dtoListToEntityList(List<GroupDTO> dtoGroups);
}

