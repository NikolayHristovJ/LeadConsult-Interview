package com.leadConsult.interview.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
public class CourseResponse {
  private Long courseId;
  private String courseName;
  private String type;
  private Set<StudentResponse> students;
  private Set<TeacherResponse> teachers;
}
