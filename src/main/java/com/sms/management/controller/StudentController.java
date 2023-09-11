package com.sms.management.controller;

import com.sms.management.dto.CreateStudentDto;
import com.sms.management.dto.GetStudentsDto;
import com.sms.management.dto.UpdateStudentDto;
import com.sms.management.entity.Student;
import com.sms.management.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public String registerNewStudent(Model model, @ModelAttribute @Valid CreateStudentDto student) {
        model.addAttribute("students", studentService.getStudents());
        studentService.addNewStudent(student);
        return "redirect:/student/students";
    }

    @DeleteMapping(path = "delete/{studentId}")
    public void deleteStudent(@PathVariable("studentId") Long studentId) {

        studentService.deleteStudent(studentId);
    }

    @PutMapping
    public void uptadeStudent(@RequestBody UpdateStudentDto student) {
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
    ) {
        studentService.deleteStudentFromCourse(studentId, courseId);
        return new ResponseEntity((HttpStatus.OK));
    }


}
