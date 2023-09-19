package com.sms.management.controller;

import com.sms.management.dto.CreateCourseDto;
import com.sms.management.dto.UpdateCourseDto;
import com.sms.management.entity.Course;
import com.sms.management.service.impl.CourseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping(path = "course")
public class CourseController {
  private final CourseService courseService;

  @GetMapping
  public List<Course> getCourses() {
    log.info("getCourses methodu çalıştı");
    return courseService.getCourses();
  }

  @GetMapping(path = "{courseId}")
  public Course getCoursesByCourseId(Long courseId) {
    log.info("get course by id: " + courseId);
    return courseService.getCourseById(courseId);
  }
  @DeleteMapping(path = "{courseId}")
  public void deleteCourse(@PathVariable("courseId") Long courseId) {
    log.info("course deleted with id: " + courseId);
    courseService.deleteCourse(courseId);
  }

  @PostMapping
  public void registerNewCourse(@RequestBody CreateCourseDto course) {
    log.info("course created with name. " + course.getCourseName());
    courseService.addNewCourse(course);
  }

  @PutMapping
  public void updateCourse(@RequestBody UpdateCourseDto course) {
    log.info("course updated with name" + course.getCourseName());
    courseService.updateCourse(course.getCourseId, course.getCourseName(), course.getCourseCode());
  }
}
