package com.sms.management.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UptadeStudentDto {

    private Long id;
    private String name;
    private String email;
    private LocalDate dob;
}

