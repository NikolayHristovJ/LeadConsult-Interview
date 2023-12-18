package com.leadConsult.interview.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentRequest {
  @NotBlank(message = "student name must not be blank or null")
  private String studentName;
  @Positive(message = "student age cannot be 0 or less")
  private Integer age;
}
