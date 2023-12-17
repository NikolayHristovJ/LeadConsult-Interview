package com.leadConsult.interview.service;

import com.leadConsult.interview.dto.request.GroupRequest;
import com.leadConsult.interview.dto.response.GroupResponse;
import com.leadConsult.interview.entity.Group;

import java.util.List;

public interface GroupService {

  Group getGroupFromRepository(Long courseId);

  List<GroupResponse> getAllGroups();

  GroupResponse postGroup(GroupRequest groupRequest);

  GroupResponse getGroupById(Long groupId);

  GroupResponse editGroup(Long groupId, GroupRequest request);
}
