package com.leadConsult.interview.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GroupRequest {
  @NotBlank(message = "group name must not be blank or null")
  private String groupName;
}
