package com.leadConsult.interview.service;

import com.leadConsult.interview.dto.request.GroupRequest;
import com.leadConsult.interview.dto.response.GroupResponse;
import com.leadConsult.interview.entity.Group;

import java.util.List;

public interface GroupService {

  /**
   *  Fetches group based on the id given if the group is not found an EntityNotFoundException will be thrown
   *
   * @param courseId
   * @return Group
   */
  Group getGroupFromRepository(Long courseId);

  /**
   * Fetches all groups
   *
   * @return Group
   */
  List<GroupResponse> getAllGroups();

  /**
   * Saves a group to the DB
   *
   * @param groupRequest
   * @return GroupResponse
   */
  GroupResponse postGroup(GroupRequest groupRequest);

  /**
   * Fetches group based on the given id if the group is not found EntityNotFoundException will be thrown
   *
   * @param groupId
   * @return GroupResponse
   */
  GroupResponse getGroupById(Long groupId);

  /**
   * Edits a specific group with the request class given and returns the old version of the group.
   * If the group is not found an EntityNotFoundException will be thrown
   *
   * @param groupId
   * @param request
   * @return GroupResponse
   */
  GroupResponse editGroup(Long groupId, GroupRequest request);
}
