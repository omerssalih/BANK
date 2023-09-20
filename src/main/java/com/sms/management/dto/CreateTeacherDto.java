package com.sms.management.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateTeacherDto {

  @Size(min = 2, max = 100)
  @NotNull
  @NotEmpty
  private String teacherName;

  @NotNull private int teacherCode;
  @NotNull private int teacherPassword;
}
