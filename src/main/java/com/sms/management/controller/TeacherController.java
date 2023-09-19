package com.sms.management.controller;

import com.sms.management.dto.*;
import com.sms.management.entity.Teacher;
import com.sms.management.service.TeacherService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/teacher")
public class TeacherController {

  private final TeacherService teacherService;

  @GetMapping
  public List<GetTeachersDto> getTeachers() {
    log.info("get teachers methodu çalıştı");
    return teacherService.getTeachers();
  }

  @GetMapping(path = "getByCourse/{courseId}")
  public ResponseEntity<List<Teacher>> getAllTeachers(@RequestParam Long courseId) {
    return new ResponseEntity<>(teacherService.getTeacherByCourse(courseId), HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity registerNewTeacher(@RequestBody CreateTeacherDto teacher) {
    teacherService.addNewTeacher(teacher);
    log.info("teacher created with name" + teacher.getTeacherName());
    return new ResponseEntity("teacher created.", HttpStatus.CREATED);
  }

  @DeleteMapping(path = "{teacherId}")
  public void deleteTeacher(@PathVariable("teacherId") Long teacherId) {
    log.info("teacher deleted with id: " + teacherId);
    teacherService.deleteTeacher(teacherId);
  }

  @DeleteMapping(path = "{teacherId}/deleteTeacherFromCourse/{courseId}")
  public ResponseEntity deleteTeacherFromCourse(
      @PathVariable("teacherId") Long teacherId, @PathVariable("courseId") Long courseId) {
    teacherService.deleteTeacherFromCourse(teacherId, courseId);
    return new ResponseEntity((HttpStatus.OK));
  }

  @PutMapping
  public void updateTeacher(@RequestBody UpdateTeacherDto teacher) {
    log.info("teacher updated with name: " + teacher.getTeacherName());
    teacherService.updateTeacher(teacher);
  }

  @PostMapping(path = "/{teacherId}/assignTeacherToCourse/{courseId}")
  public ResponseEntity assignCourseToTeacher(
      @PathVariable Long teacherId, @PathVariable Long courseId) {
    log.info("teacher assign course with id: " + teacherId);
    teacherService.assignCourseToTeacher(teacherId, courseId);
    return new ResponseEntity(HttpStatus.CREATED);
  }
}
