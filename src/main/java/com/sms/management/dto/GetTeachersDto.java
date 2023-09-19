package com.sms.management.dto;

import com.sms.management.entity.Course;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetTeachersDto implements Serializable {

  private Long teacherId;
  private String teacherName;
  private List<Course> assignedTeacherCourses;
  private int teacherCode;
}
