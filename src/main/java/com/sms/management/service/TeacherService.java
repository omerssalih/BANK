package com.sms.management.service;

import com.sms.management.dto.CreateTeacherDto;
import com.sms.management.dto.GetTeachersDto;
import com.sms.management.dto.UpdateTeacherDto;
import com.sms.management.entity.Teacher;
import java.util.List;
import org.springframework.cache.annotation.Cacheable;

public interface TeacherService {
  void addNewTeacher(CreateTeacherDto createTeacherDto);

  @Cacheable(value = "Teachers")
  List<GetTeachersDto> getTeachers();

  void deleteTeacher(Long teacherId);

  void deleteTeacherFromCourse(Long teacherId, Long courseId);

  void updateTeacher(UpdateTeacherDto teacherDto);

  void assignCourseToTeacher(Long teacherId, Long courseId);

  List<Teacher> getTeacherByCourse(Long courseId);
}
