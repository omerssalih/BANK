package com.sms.management.service;

import com.sms.management.dto.CreateCourseDto;
import com.sms.management.entity.Course;
import java.util.List;
import org.springframework.cache.annotation.Cacheable;

public interface CourseService {
  @Cacheable(value = "courses")
  List<Course> getCourses();

  void deleteCourse(Long courseId);

  void addNewCourse(CreateCourseDto createCourseDto);

  Course getCourseById(Long courseId);

  void updateCourse(Long courseId, String courseName, int courseCode);
}
