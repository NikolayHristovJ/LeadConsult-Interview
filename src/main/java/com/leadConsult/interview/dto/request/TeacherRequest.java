package com.leadConsult.interview.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TeacherRequest {
  @NotBlank(message = "teacher name must not be blank or null")
  private String teacherName;
  @Positive(message = "teacher age cannot be 0 or less")
  private Integer age;
}
