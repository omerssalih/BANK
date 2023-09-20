package com.sms.management.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateStudentDto {

  @Size(min = 2, max = 100)
  @NotNull
  @NotEmpty
  private String name;

  @Email @NotNull @NotEmpty private String email;
  @NotNull private LocalDate dob;
}
