package com.sms.management.service;

import com.sms.management.dto.CreateStudentDto;
import com.sms.management.dto.GetStudentsDto;
import com.sms.management.entity.Student;
import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.cache.annotation.Cacheable;

public interface StudentService {
  @Cacheable(value = "students")
  List<GetStudentsDto> getStudents();

  List<Student> getStudentsById(Long studentId);

  void addNewStudent(CreateStudentDto createStudentDto);

  void deleteStudent(Long studentId);

  @Transactional
  void updateStudent(Long studentId, String name, String email);

  void assignCourseToStudent(Long studentId, Long courseId);

  void deleteStudentFromCourse(Long studentId, Long courseId);

  List<Student> getStudentsByCourse(Long courseId);

  Long getNumberOfStudentsEnrolledInCourse(Long courseId);
}
