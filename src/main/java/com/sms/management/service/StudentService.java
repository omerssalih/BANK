package com.sms.management.service;

import com.sms.management.entity.Course;
import com.sms.management.entity.Student;
import com.sms.management.exception.StudentNotFoundException;
import com.sms.management.repository.CourseRepository;
import com.sms.management.repository.StudentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final CourseService courseService;

    public List<Student> getStudents() {
        return studentRepository.findAll();

    }

    public void addNewStudent(Student student) {
        Optional<Student> studentOptional = studentRepository
                .findStudentByEmail(student.getEmail());
        if(studentOptional.isPresent()){
            throw new IllegalStateException("email taken");
        }
        studentRepository.save(student);
    }

    public void deleteStudent(Long studentId) {
        boolean exists = studentRepository.existsById(studentId);
        if(!exists){
            throw new IllegalStateException(
                    "student with id" + studentId + "does not exists");
        }
        studentRepository.deleteById(studentId);
    }

    @Transactional
    public void uptadeStudent(Long studentId, String name, String email) {
        Student student = studentRepository.findById(studentId).
                orElseThrow(()-> new IllegalStateException("student with id" + studentId + "does not exists"));
        if(name != null && name.length() > 0 &&!Objects.equals(student.getName(), name)){
            student.setName(name);
        }
        if(email != null && email.length() > 0 &&!Objects.equals(student.getEmail(), email)){
            Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);
            if(studentOptional.isPresent()){
                throw new IllegalStateException("email taken");
            }
            student.setEmail(email);
        }
    }


    public void assignCourseToStudent(Long studentId, Long courseId) {
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new StudentNotFoundException(
                "student not found with id: " + studentId));
        Course course = courseService.getCourseById(courseId);
        student.getAssignedCourses().add(course);
        studentRepository.save(student);

    }
}
