package com.sms.management.controller;

import com.sms.management.dto.*;
import com.sms.management.entity.Course;
import com.sms.management.entity.Teacher;
import com.sms.management.exception.AuthenticationException;
import com.sms.management.exception.StudentNotFoundException;
import com.sms.management.repository.TeacherRepository;
import com.sms.management.service.TeacherService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@EntityScan
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/teacher")
public class TeacherController {

    private final TeacherService teacherService;
    private final TeacherRepository teacherRepository;

    @GetMapping
    public List<GetTeachersDto> getTeachers() {
        log.info("get teachers methodu çalıştı");
        return teacherService.getTeachers();
    }

    @PostMapping(path = "/addNewTeacher/new")
    public ResponseEntity registerNewTeacher(Model model, @RequestBody @Valid CreateTeacherDto teacher) {
        teacherService.addNewTeacher(teacher);
        log.info("teacher created with name" + teacher.getTeacherName() );
        return new ResponseEntity("teacher created.", HttpStatus.CREATED);
    }

    @DeleteMapping(path = "deleteTeacher/{teacherId}")
    public void deleteTeacher(@PathVariable("teacherId") Long teacherId) {
        teacherService.deleteTeacher(teacherId);
    }

    @PutMapping(path = "updateTeacher/{teacherCode}")
    public void updateTeacher(@RequestBody UpdateTeacherDto teacher) {
        teacherService.updateTeacher(teacher);
    }

    @PostMapping(path = "/{teacherId}/course/{courseId}")
    public ResponseEntity assignCourseToTeacher(
            @PathVariable Long teacherId,
            @PathVariable Long courseId
    ) {
        teacherService.assignCourseToTeacher(teacherId, courseId);
        return new ResponseEntity(HttpStatus.CREATED);
    }
}
