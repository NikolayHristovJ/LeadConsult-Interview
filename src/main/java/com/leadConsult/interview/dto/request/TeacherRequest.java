package com.leadConsult.interview.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TeacherRequest {
  private String teacherName;
  private Integer age;
  private GroupRequest group;
}
