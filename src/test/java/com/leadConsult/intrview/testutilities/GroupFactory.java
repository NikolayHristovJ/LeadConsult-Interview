package com.leadConsult.intrview.testutilities;

import com.leadConsult.interview.dto.request.GroupRequest;
import com.leadConsult.interview.dto.response.GroupResponse;
import com.leadConsult.interview.entity.Group;

import static com.leadConsult.intrview.testutilities.GroupConstants.GROUP_ID;
import static com.leadConsult.intrview.testutilities.GroupConstants.GROUP_NAME;

public class GroupFactory {
  public static Group getDefaultGroup() {
    return new Group(GROUP_ID,
                       GROUP_NAME,
                       null,
                       null
    );
  }

  public static GroupRequest getDefaultGroupRequest() {
    return new GroupRequest(GROUP_NAME);
  }

  public static GroupResponse getDefaultGroupResponse() {
    return new GroupResponse(GROUP_ID, GROUP_NAME);
  }
}
