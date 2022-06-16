package peaksoft.company_restapi.API;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import peaksoft.company_restapi.DTO.GroupDTO;
import peaksoft.company_restapi.exceptions.BadRequestException;
import peaksoft.company_restapi.exceptions.NotFoundException;
import peaksoft.company_restapi.models.Group;
import peaksoft.company_restapi.responses.Response;
import peaksoft.company_restapi.services.GroupService;

import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/company/group")
public class GroupAPI {

    private final GroupService groupService;

    @PostMapping("/save/{companyId}")
    public Response saveGroup(@PathVariable("companyId") Long companyId,
                              @RequestBody GroupDTO group){
        return groupService.saveGroup(group, companyId);
    }

    @GetMapping("/getGroup")
    public List<GroupDTO> getAllGroups(){
        return groupService.findAllGroups();
    }

    @GetMapping("/get/{groupId}")
    public GroupDTO getGroupById(@PathVariable("groupId") Long groupId){
        return groupService.getGroupById(groupId);
    }

    @DeleteMapping("/delete/{groupId}")
    public Response deleteGroupById(@PathVariable("groupId") Long groupId){
        return groupService.deleteGroupById(groupId);
    }

    @PatchMapping("/update/{groupId}")
    public Response updateGroupId(@PathVariable("groupId") Long groupId,
                                  @RequestBody Group group){
        return groupService.updateGroupById(groupId, group);
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(BAD_REQUEST)
    public Response handleBadRequestException(BadRequestException badRequestException) {
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
