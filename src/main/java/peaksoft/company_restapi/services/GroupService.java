package peaksoft.company_restapi.services;


import peaksoft.company_restapi.DTO.GroupDTO;
import peaksoft.company_restapi.models.Group;
import peaksoft.company_restapi.responses.Response;

import javax.transaction.Transactional;
import java.util.List;

public interface GroupService {
    Response saveGroup(GroupDTO group, Long companyId);

    void doesGroupExist(Group group);

    List<GroupDTO> findAllGroups();

    GroupDTO getGroupById (Long id);

    @Transactional
    Response updateGroupById(Long id, Group newGroup);

    Response deleteGroupById(Long id);
}
