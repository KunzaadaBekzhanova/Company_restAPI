package peaksoft.company_restapi.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import peaksoft.company_restapi.DTO.GroupDTO;
import peaksoft.company_restapi.exceptions.BadRequestException;
import peaksoft.company_restapi.exceptions.NotFoundException;
import peaksoft.company_restapi.mappers.GroupMapper;
import peaksoft.company_restapi.models.Group;
import peaksoft.company_restapi.repo.CompanyRepository;
import peaksoft.company_restapi.repo.CourseRepository;
import peaksoft.company_restapi.repo.GroupRepository;
import peaksoft.company_restapi.responses.Response;
import peaksoft.company_restapi.services.GroupService;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.HttpStatus.RESET_CONTENT;

@Service
@RequiredArgsConstructor
@Slf4j
public class GroupServiceImpl implements GroupService {
    private final GroupRepository groupRepository;
    private final CourseRepository courseRepository;
    private final CompanyRepository companyRepository;
    private final GroupMapper groupMapper;

    @Override
    public Response saveGroup(GroupDTO groupDTO, Long companyId) {
        Group group = groupMapper.dtoToEntity(groupDTO);
        List<Long> coursesNumbers = new ArrayList<>();
        if(group.getCoursesChoice()!=null){
            for (int i = 0; i<group.getCoursesChoice().size(); i++){
                coursesNumbers.add(Long.valueOf(group.getCoursesChoice().get(i)));
            }
        }
        for(Long i: coursesNumbers) {
            if(group.getCourses()==null){
                group.setCourses(new ArrayList<>());
            }
            group.getCourses().add(courseRepository.getById(i));
        }
        doesGroupExist(group);
        group.setCompany(companyRepository.getById(companyId));
        try{
            groupRepository.save(group);
        }
        catch (Exception e){
            throw new NotFoundException(String.format("There is no company with id: %s or " +
                    "there is no course with one of these ids %s", companyId, coursesNumbers));
        }
        return Response.builder()
                .httpStatus(HttpStatus.CREATED)
                .message(String.format("Group with name %s, successfully created ", group.getGroupName()))
                .build();
    }

    @Override
    public void doesGroupExist(Group group){
        if(groupRepository.existsByGroupName(group.getGroupName())){
            log.warn("Group with group name: {}-> already exists",
                    group.getGroupName());
            throw new BadRequestException(
                    "Group with group name: " + group.getGroupName()
            );
        }
    }

    @Override
    public List<GroupDTO> findAllGroups() {
        return groupMapper.entityListToDtoList(groupRepository.findAll());
    }

    @Override
    public GroupDTO getGroupById(Long id) {
        Group group = groupRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Group with id = {} does not exist", id);
                    throw new NotFoundException(
                            String.format("Group with id = %s does not exist", id)
                    );
                });
        log.info("Founded Course with id = {}", id);
        return groupMapper.entityToDto(group);
    }

    @Override
    @Transactional
    public Response updateGroupById(Long id, Group newGroup) {
        Group group = groupRepository.getById(id);

        String groupName = group.getGroupName();
        String newGroupName = newGroup.getGroupName();
        if (!Objects.equals(groupName, newGroupName)) {
            group.setGroupName(newGroupName);
            log.info("Group with id = {} changed name from {} to {}",
                    id, groupName, newGroupName);
        }

        LocalDate dateOfStart = group.getDateOfStart();
        LocalDate newDateOfStart = newGroup.getDateOfStart();
        if (!Objects.equals(dateOfStart, newDateOfStart)) {
            group.setDateOfStart(newDateOfStart);
            log.info("Group with id = {} changed date of start from {} to {}",
                    id, dateOfStart, newDateOfStart);
        }

        LocalDate dateOfFinish = group.getDateOfFinish();
        LocalDate newDateOfFinish = newGroup.getDateOfFinish();
        if (!Objects.equals(dateOfFinish, newDateOfFinish)) {
            group.setDateOfFinish(newDateOfFinish);
            log.info("Group with id = {} changed date of  finish {} to {}",
                    id, dateOfFinish, newDateOfFinish);
        }

        String message = String.format("Group with id = %s has successfully been updated", id);
        return Response.builder()
                .httpStatus(RESET_CONTENT)
                .message(message)
                .build();
    }

    @Override
    public Response deleteGroupById(Long id) {
        try{
            groupRepository.deleteById(id);
        }
        catch(Exception e){
            throw new NotFoundException(String.format("Group with id %s has not been found", id));
        }
        log.info("Group with id = {} has successfully been deleted", id);
        String message = String.format("Group with id = %s has successfully been deleted", id);
        return Response.builder()
                .httpStatus(OK)
                .message(message)
                .build();
    }
}

