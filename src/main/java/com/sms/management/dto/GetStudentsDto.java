package com.sms.management.dto;

import java.io.Serializable;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetStudentsDto implements Serializable {
  private Long id;
  private String name;
  private int age;
  private LocalDate dob;
  private String email;
}
