package com.sms.management.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateTeacherDto {

    private int teacherCode;
    private int inputPassword;
    private String teacherName;
    private String teacherCourse;
}
