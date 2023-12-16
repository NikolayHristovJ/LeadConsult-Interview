package com.leadConsult.interview.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
public class GroupResponse {
  private Long groupId;
  private String groupName;
  private Set<StudentResponse> students;
  private Set<TeacherResponse> teachers;

}
