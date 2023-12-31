package com.leadConsult.interview.controller;

import com.leadConsult.interview.dto.request.GroupRequest;
import com.leadConsult.interview.dto.response.GroupResponse;
import com.leadConsult.interview.service.GroupService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/api/groups")
public class GroupController {
  private final GroupService groupService;

  @Autowired
  public GroupController(GroupService groupService) {
    this.groupService = groupService;
  }

  @GetMapping
  public ResponseEntity<List<GroupResponse>> getAllGroups (){
    List<GroupResponse> response = groupService.getAllGroups();
    return ResponseEntity.ok(response);
  }

  @Operation(
    description = "Add a Group to the DB",
    responses = {
      @ApiResponse(
        description = "Success",
        responseCode = "200"
      ),
      @ApiResponse(
        description = "Failure you tried to post with data that is not valid",
        responseCode = "400"
      )
    }
  )
  @PostMapping
  public ResponseEntity<GroupResponse> postGroup(@RequestBody @Valid GroupRequest groupRequest){
    GroupResponse response = groupService.postGroup(groupRequest);
    return new ResponseEntity<>(response, HttpStatus.CREATED);
  }

  @GetMapping("/{groupId}")
  public ResponseEntity<GroupResponse> getGroupById(@PathVariable Long groupId){
    GroupResponse response = groupService.getGroupById(groupId);
    return ResponseEntity.ok(response);
  }

  @Operation(
    description = "Edit a Group",
    responses = {
      @ApiResponse(
        description = "Success",
        responseCode = "200"
      ),
      @ApiResponse(
        description = "Success but with no body",
        responseCode = "204"
      ),
      @ApiResponse(
        description = "Failure course id not found",
        responseCode = "404"
      ),
      @ApiResponse(
        description = "Failure you tried to edit with data that is not valid",
        responseCode = "400"
      )
    }
  )
  @PutMapping("/{groupId}")
  public ResponseEntity<GroupResponse> editGroup(@RequestBody @Valid GroupRequest request,
                                                    @PathVariable Long groupId,
                                                    @RequestParam(required = false) boolean returnOld){
    GroupResponse response = groupService.editGroup(groupId,request);
    if (returnOld) {
      return ResponseEntity.ok(response);
    } else {
      return ResponseEntity.noContent().build();
    }
  }
}
