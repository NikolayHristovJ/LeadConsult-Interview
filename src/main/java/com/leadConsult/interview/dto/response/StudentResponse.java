package com.leadConsult.interview.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StudentResponse {
  private Long studentId;
  private String studentName;
  private Integer age;
  private GroupResponse group;
}
