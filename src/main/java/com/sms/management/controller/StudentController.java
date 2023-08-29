package com.sms.management.controller;

import com.sms.management.entity.Student;
import com.sms.management.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/student")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> getStudents() {
        return studentService.getStudents();

    }
    @PostMapping
    public void registerNewStudent(@RequestBody Student student){

        studentService.addNewStudent(student);
    }

    @DeleteMapping(path = "{studentId}")
    public void deleteStudent(@PathVariable("studentId") Long studentId){

        studentService.deleteStudent(studentId);
    }

    @PutMapping
    public void uptadeStudent(@RequestBody Student student){
        studentService.uptadeStudent(student.getId(), student.getName(), student.getEmail());
    }

    @PostMapping ("/{studentId}/course/{courseId}")
    public ResponseEntity assignCourseToStudent(
            @PathVariable Long studentId,
            @PathVariable Long courseId
    ){
        studentService.assignCourseToStudent(studentId, courseId);
        return new ResponseEntity(HttpStatus.CREATED);
    }

}
