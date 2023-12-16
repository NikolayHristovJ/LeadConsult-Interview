package com.leadConsult.interview.service.impl;

import com.leadConsult.interview.dto.request.GroupRequest;
import com.leadConsult.interview.dto.response.GroupResponse;
import com.leadConsult.interview.entity.Group;
import com.leadConsult.interview.mapper.GroupMapper;
import com.leadConsult.interview.repository.GroupRepository;
import com.leadConsult.interview.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupServiceImpl implements GroupService {
  private final GroupRepository groupRepository;
  private final GroupMapper groupMapper;

  @Autowired
  public GroupServiceImpl(GroupRepository groupRepository, GroupMapper groupMapper) {
    this.groupRepository = groupRepository;
    this.groupMapper = groupMapper;
  }

  @Override
  public List<GroupResponse> getAllGroups() {
    List<Group> groups = groupRepository.findAll();
    return groupMapper.listGroupToListGroupResponse(groups);
  }

  @Override
  public GroupResponse postGroup(GroupRequest groupRequest) {
    Group group = groupMapper.groupRequestToGroup(groupRequest);
    groupRepository.save(group);

    return groupMapper.groupToGroupResponse(group);
  }
}
