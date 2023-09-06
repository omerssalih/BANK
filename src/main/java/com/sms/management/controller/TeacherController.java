package com.sms.management.controller;

import com.sms.management.entity.Teacher;
import com.sms.management.repository.TeacherRepository;
import com.sms.management.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@EntityScan
@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/teacher")
public class TeacherController {

    private final TeacherService teacherService;

    @Autowired
    private TeacherRepository teacherRepository;

    @PostMapping(path = "addNewTeacher")
    public void registerNewTeacher(Teacher teacher){
        teacherService.addNewTeacher(teacher);
    }

    @GetMapping(path = "teachers")
    public List<Teacher> getTeachers(){
        return teacherService.getTeachers();
    }

    @DeleteMapping(path = "deleteTeacher/{teacherId}")
    public void deleteTeacher(@PathVariable("teacherId") Long teacherId){
        teacherService.deleteTeacher(teacherId);
    }

    @PutMapping(path = "updateTeacher/{teacherId}")
    public void updateTeacher(@PathVariable("teacherId") Long teacherId, @RequestBody Teacher teacher){
        teacherService.updateTeacher(teacher.getTeacherId(), teacher.getTeacherName(), teacher.getTeacherCourse() );
    }

    @PostMapping(path = "/{teacherId}/course/{courseId}")
    public ResponseEntity assignCourseToTeacher(
            @PathVariable Long teacherId,
            @PathVariable Long courseId
    ){
        teacherService.assignCourseToTeacher(teacherId,courseId);
        return new ResponseEntity(HttpStatus.CREATED);
    }
}
