package com.sms.management.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.time.LocalDate;

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
