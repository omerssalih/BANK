package com.sms.management.controller;

import com.sms.management.dto.CreateStudentDto;
import com.sms.management.dto.GetStudentsDto;
import com.sms.management.dto.UptadeStudentDto;
import com.sms.management.entity.Student;
import com.sms.management.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/student")
public class StudentController {

    private final StudentService studentService;

    @GetMapping
    public List<GetStudentsDto> getStudents() {
        return studentService.getStudents();
    }

    @GetMapping(path = {"studentId"})
    public List<Student> getStudentsById(Long id) {

        return studentService.getStudentsById(id);
    }
     //TODO DÜZELİCEK
    /*@GetMapping(path = "getStudentByCourse")
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> students = studentService.getStudents();
        List<Student> enrolledStudents = new ArrayList<>();
        for (Student student : students) {
            if (!student.getAssignedCourses().isEmpty()) {
                enrolledStudents.add(student);
            }
        }
        return ResponseEntity.ok(enrolledStudents);
    }*/

    @PostMapping("/students/new")
    public String registerNewStudent(Model model, @ModelAttribute  @Valid CreateStudentDto student) {
        model.addAttribute("students", studentService.getStudents());
        studentService.addNewStudent(student);
        return "redirect:/student/students";
    }

    @DeleteMapping(path = "delete/{studentId}")
    public void deleteStudent(@PathVariable("studentId") Long studentId) {

        studentService.deleteStudent(studentId);
    }

    @PutMapping
    public void uptadeStudent(@RequestBody UptadeStudentDto student) {
        studentService.uptadeStudent(student.getId(), student.getName(), student.getEmail());
    }

    @PostMapping("/{studentId}/course/{courseId}")
    public ResponseEntity assignCourseToStudent(
            @PathVariable Long studentId,
            @PathVariable Long courseId
    ) {
        studentService.assignCourseToStudent(studentId, courseId);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @DeleteMapping("/{studentId}/deleteStudentFromCourse/{courseId}")
    public ResponseEntity deleteStudentFromCourse(
            @PathVariable Long studentId,
            @PathVariable Long courseId
    ){
        studentService.deleteStudentFromCourse(studentId,courseId);
        return new ResponseEntity((HttpStatus.OK));
    }

    @GetMapping("/students")
    public String listStudents(Model model){
        model.addAttribute("students", studentService.getStudents());
        return "students";
    }
    @GetMapping("/students/new")
    public String addNewStudentPage(Model model){
        return "create_student";
    }

}
