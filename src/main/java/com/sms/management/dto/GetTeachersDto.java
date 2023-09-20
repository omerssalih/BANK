package com.sms.management.dto;

import com.sms.management.entity.Course;
import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetTeachersDto implements Serializable {

  private Long teacherId;
  private String teacherName;
  private List<Course> assignedTeacherCourses;
  private int teacherCode;
}
