package com.leadConsult.interview.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StudentRequest {
  private String studentName;
  private Integer age;
}
