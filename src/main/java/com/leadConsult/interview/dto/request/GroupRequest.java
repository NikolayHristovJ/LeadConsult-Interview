package com.leadConsult.interview.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GroupRequest {
  private Long groupId;
  private String groupName;
}