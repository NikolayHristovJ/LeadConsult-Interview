package com.leadConsult.interview.mapper;

import com.leadConsult.interview.dto.request.GroupRequest;
import com.leadConsult.interview.dto.response.GroupResponse;
import com.leadConsult.interview.entity.Group;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GroupMapper {
  Group groupRequestToGroup(GroupRequest request);
  GroupResponse groupToGroupResponse (Group group);
  List<GroupResponse> listGroupToListGroupResponse (List<Group> groups);
}
