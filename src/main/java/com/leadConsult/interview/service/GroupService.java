package com.leadConsult.interview.service;

import com.leadConsult.interview.dto.request.GroupRequest;
import com.leadConsult.interview.dto.response.GroupResponse;

import java.util.List;

public interface GroupService {

  List<GroupResponse> getAllGroups();

  GroupResponse postGroup(GroupRequest groupRequest);
}
