package com.sms.management.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateTeacherDto {

  private int teacherCode;
  private int inputPassword;
  private String teacherName;
  private Long courseId;
}
