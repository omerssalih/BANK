package com.sms.management.dto;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateStudentDto {

  private Long id;
  private String name;
  private String email;
  private LocalDate dob;
}
