package com.leadConsult.interview.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CourseRequest {
  @NotBlank
  private String courseName;
  @NotBlank
  @Pattern(regexp = "(?i)\\bmain\\b|\\bsecondary\\b", message = "the course type should be (main or secondary)")
  private String type;
}
