package com.sms.management.controller;

import com.sms.management.dto.CreateStudentDto;
import com.sms.management.dto.GetStudentsDto;
import com.sms.management.dto.UpdateStudentDto;
import com.sms.management.entity.Student;
import com.sms.management.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/student")
public class StudentController {

    private final StudentService studentService;

    @GetMapping
    public List<GetStudentsDto> getStudents() {
        log.info("get students methodu çalıştı: ");
        return studentService.getStudents();
    }

    @GetMapping(path = {"{studentId}"})
    public List<Student> getStudentsById(@RequestParam Long studentId) {
        log.info("getStudentsById methodu çalıştı" + studentId);
        return studentService.getStudentsById(studentId);
    }
    @GetMapping(path = "getStudentByCourse/{courseId}")
    public ResponseEntity<List<Student>> getAllStudents(@RequestParam Long courseId) {
        return new ResponseEntity<>(studentService.getStudentsByCourse(courseId), HttpStatus.OK);
    }

    @PostMapping("/students/new")
    public ResponseEntity registerNewStudent( @RequestBody @Valid CreateStudentDto student) {
        log.info("student creating with name" + student.getName() );
        studentService.addNewStudent(student);
        return ResponseEntity.ok("");
    }

    @DeleteMapping(path = "delete/{studentId}")
    public void deleteStudent(@PathVariable("studentId") Long studentId) {
        log.info("student deleted with id" + studentId );
        studentService.deleteStudent(studentId);
    }

    @PutMapping
    public void uptadeStudent(@RequestBody UpdateStudentDto student) {
        log.info("student updated with name" + student.getName() );
        studentService.uptadeStudent(student.getId(), student.getName(), student.getEmail());
    }

    @PostMapping("/{studentId}/course/{courseId}")
    public ResponseEntity assignCourseToStudent(
            @PathVariable Long studentId,
            @PathVariable Long courseId
    ) {
        log.info("student assign course with id" + studentId );
        studentService.assignCourseToStudent(studentId, courseId);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @DeleteMapping("/{studentId}/deleteStudentFromCourse/{courseId}")
    public ResponseEntity deleteStudentFromCourse(
            @PathVariable Long studentId,
            @PathVariable Long courseId
    ) {
        log.info("student deleted from course with id" + studentId);
        studentService.deleteStudentFromCourse(studentId, courseId);
        return new ResponseEntity((HttpStatus.OK));
    }


}
