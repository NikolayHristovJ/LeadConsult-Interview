package com.leadConsult.interview.dto.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CourseRequest {
  private String courseName;
  private String type;
}
