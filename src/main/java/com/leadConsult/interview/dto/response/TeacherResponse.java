package com.leadConsult.interview.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TeacherResponse {
  private Long teacherId;
  private String teacherName;
  private Integer age;
  private GroupResponse group;
}
