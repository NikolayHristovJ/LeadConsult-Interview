package com.leadConsult.interview.service.impl;

import com.leadConsult.interview.dto.request.GroupRequest;
import com.leadConsult.interview.dto.response.GroupResponse;
import com.leadConsult.interview.entity.Group;
import com.leadConsult.interview.mapper.GroupMapper;
import com.leadConsult.interview.repository.GroupRepository;
import com.leadConsult.interview.service.GroupService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupServiceImpl implements GroupService {
  private static final Logger log = LoggerFactory.getLogger(GroupServiceImpl.class);
  private final GroupRepository groupRepository;
  private final GroupMapper groupMapper;

  @Autowired
  public GroupServiceImpl(GroupRepository groupRepository, GroupMapper groupMapper) {
    this.groupRepository = groupRepository;
    this.groupMapper = groupMapper;
  }

  @Override
  public Group getGroupFromRepository(Long groupId) {
    return groupRepository.findById(groupId)
                           .orElseThrow(
                             () -> new EntityNotFoundException(String.format("Group not found with id: %s", groupId)));
  }

  @Override
  public List<GroupResponse> getAllGroups() {
    log.info("Fetching all groups");
    List<Group> groups = groupRepository.findAll();
    return groupMapper.listGroupToListGroupResponse(groups);
  }

  @Override
  public GroupResponse postGroup(GroupRequest groupRequest) {
    log.info("Saving group to DB");
    Group group = groupMapper.groupRequestToGroup(groupRequest);
    groupRepository.save(group);

    return groupMapper.groupToGroupResponse(group);
  }

  @Override
  public GroupResponse getGroupById(Long groupId) {
    log.info(String.format("Fetching group with id: %s",groupId));
    Group group = getGroupFromRepository(groupId);

    return groupMapper.groupToGroupResponse(group);
  }

  @Override
  @Transactional
  public GroupResponse editGroup(Long groupId, GroupRequest request) {
    log.info(String.format("Editing group with id: %s",groupId));
    Group oldGroup = getGroupFromRepository(groupId);

    Group editedGroup = groupMapper.groupRequestToGroup(request);
    editedGroup.setGroupId(groupId);
    editedGroup.setStudents(oldGroup.getStudents());
    editedGroup.setTeachers(oldGroup.getTeachers());
    GroupResponse response = groupMapper.groupToGroupResponse(oldGroup);
    groupRepository.save(editedGroup);

    return response;
  }
}
