package com.leadConsult.interview.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
public class TeacherResponse {
  private Long teacherId;
  private String teacherName;
  private Integer age;
  private Set<CourseResponse> teachersCourses;
  private GroupResponse group;
}
