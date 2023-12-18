package com.leadConsult.intrview.service;

import com.leadConsult.interview.dto.request.GroupRequest;
import com.leadConsult.interview.dto.response.GroupResponse;
import com.leadConsult.interview.entity.Group;
import com.leadConsult.interview.mapper.GroupMapper;
import com.leadConsult.interview.repository.GroupRepository;
import com.leadConsult.interview.service.impl.GroupServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static com.leadConsult.intrview.testutilities.GroupConstants.GROUP_ID;
import static com.leadConsult.intrview.testutilities.GroupFactory.getDefaultGroup;
import static com.leadConsult.intrview.testutilities.GroupFactory.getDefaultGroupRequest;
import static com.leadConsult.intrview.testutilities.GroupFactory.getDefaultGroupResponse;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GroupServiceImplTest {
  private Group group;
  private GroupRequest groupRequest;
  private GroupResponse groupResponse;

  @Mock
  private GroupRepository groupRepository;

  @Mock
  private GroupMapper groupMapper;

  @InjectMocks
  private GroupServiceImpl groupService;

  @Before
  public void setup() {
    group = getDefaultGroup();
    groupResponse = getDefaultGroupResponse();
    groupRequest = getDefaultGroupRequest();
  }

  @Test
  public void getGroupById_success(){
    when(groupRepository.findById(GROUP_ID)).thenReturn(Optional.ofNullable(group));
    when(groupMapper.groupToGroupResponse(group)).thenReturn(groupResponse);

    GroupResponse actual = groupService.getGroupById(GROUP_ID);

    assertEquals(groupResponse.getGroupId(), actual.getGroupId());
    assertEquals(groupResponse.getGroupName(), actual.getGroupName());
  }

  @Test(expected = EntityNotFoundException.class)
  public void getGroupById_groupNotFoundException_fail() {
    when(groupRepository.findById(GROUP_ID)).thenReturn(Optional.empty());

    groupService.getGroupById(GROUP_ID);
  }

  @Test
  public void addNewGroup_success(){
    when(groupMapper.groupRequestToGroup(groupRequest)).thenReturn(group);
    when(groupMapper.groupToGroupResponse(group)).thenReturn(groupResponse);

    GroupResponse actual = groupService.postGroup(groupRequest);

    assertEquals(groupResponse.getGroupId(), actual.getGroupId());
    assertEquals(groupResponse.getGroupName(), actual.getGroupName());

    verify(groupRepository).save(any());
  }
}
