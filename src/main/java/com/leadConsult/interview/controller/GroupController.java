package com.leadConsult.interview.controller;

import com.leadConsult.interview.dto.request.GroupRequest;
import com.leadConsult.interview.dto.response.GroupResponse;
import com.leadConsult.interview.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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

  @PostMapping
  public ResponseEntity<GroupResponse> postGroup(@RequestBody GroupRequest groupRequest){
    GroupResponse response = groupService.postGroup(groupRequest);
    return new ResponseEntity<>(response, HttpStatus.CREATED);
  }

  @GetMapping("/{groupId}")
  public ResponseEntity<GroupResponse> getGroupById(@PathVariable Long groupId){
    GroupResponse response = groupService.getGroupById(groupId);
    return ResponseEntity.ok(response);
  }
}
