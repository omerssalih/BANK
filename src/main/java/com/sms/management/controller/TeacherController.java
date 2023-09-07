package com.sms.management.controller;


import com.sms.management.dto.CreateTeacherDto;
import com.sms.management.dto.GetStudentsDto;
import com.sms.management.dto.GetTeachersDto;
import com.sms.management.entity.Course;
import com.sms.management.entity.Teacher;
import com.sms.management.repository.TeacherRepository;
import com.sms.management.service.TeacherService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@EntityScan
@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/teacher")
public class TeacherController {

    private final TeacherService teacherService;

    @Autowired
    private TeacherRepository teacherRepository;

    @GetMapping
    public List<GetTeachersDto> getTeachers(){
        return teacherService.getTeachers();

    }
    /*
    @GetMapping(path = "teachers")
    public List<Teacher> getTeachers(){
        return teacherService.getTeachers();
    }*/

    @PostMapping(path = "/addNewTeacher/new")
    public ResponseEntity registerNewTeacher(Model model, @RequestBody @Valid CreateTeacherDto teacher){
        teacherService.addNewTeacher(teacher);
        return new ResponseEntity("teacher created.", HttpStatus.CREATED);
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
