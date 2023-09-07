package com.sms.management.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetTeachersDto {
    private Long teacherId;
    private String teacherName;
    private String teacherCourse;

}
