package com.sms.management.service;

import com.sms.management.dto.CreateCourseDto;
import com.sms.management.entity.Course;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

public interface CourseService {
  @Cacheable(value = "courses")
  List<Course> getCourses();

  void deleteCourse(Long courseId);

  void addNewCourse(CreateCourseDto createCourseDto);

  Course getCourseById(Long courseId);

  void updateCourse(Long courseId, String courseName, int courseCode);
}
