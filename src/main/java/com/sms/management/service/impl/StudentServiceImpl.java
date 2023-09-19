package com.sms.management.service.impl;

import com.sms.management.dto.CreateStudentDto;
import com.sms.management.dto.GetStudentsDto;
import com.sms.management.entity.Course;
import com.sms.management.entity.Student;
import com.sms.management.exception.SMSException;
import com.sms.management.exception.StudentNotFoundException;
import com.sms.management.repository.StudentRepository;
import com.sms.management.service.StudentService;
import jakarta.transaction.Transactional;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

  private final StudentRepository studentRepository;
  private final CourseServiceImpl courseServiceImpl;
  private final ModelMapper modelMapper;

  @Override
  @Cacheable(value = "students")
  public List<GetStudentsDto> getStudents() {
    log.info("get students methodu redis ile çalıştı");
    List<Student> students = studentRepository.findAll();
    List<GetStudentsDto> result = getGetStudentDtos(students);
    return result;
  }

  public List<GetStudentsDto> getGetStudentDtos(List<Student> students) {
    List<GetStudentsDto> result = new ArrayList<>();
    students.forEach(
        student -> {
          LocalDate dob = student.getDob();
          LocalDate now = LocalDate.now();
          int age = Period.between(dob, now).getYears();
          GetStudentsDto getStudentDto = modelMapper.map(student, GetStudentsDto.class);
          getStudentDto.setAge(age);
          result.add(getStudentDto);
        });
    return result;
  }

  @Override
  public List<Student> getStudentsById(Long studentId) {
    log.info("redis deneme" + studentId);
    return studentRepository.findAllById(Collections.singleton(studentId));
  }

  @Override
  public void addNewStudent(CreateStudentDto createStudentDto) {
    Optional<Student> studentOptional =
        studentRepository.findStudentByEmail(createStudentDto.getEmail());
    if (studentOptional.isPresent()) {
      throw new IllegalStateException("email taken");
    }
    Student student = modelMapper.map(createStudentDto, Student.class);
    studentRepository.save(student);
  }

  @Override
  public void deleteStudent(Long studentId) {
    boolean exists = studentRepository.existsById(studentId);
    if (!exists) {
      throw new IllegalStateException("student with id" + studentId + "does not exists");
    }
    studentRepository.deleteById(studentId);
  }

  @Override
  @Transactional
  public void updateStudent(Long studentId, String name, String email) {
    Student student =
        studentRepository
            .findById(studentId)
            .orElseThrow(
                () -> new IllegalStateException("student with id" + studentId + "does not exists"));
    if (studentRepository.existsByEmail(email)) {
      throw new IllegalStateException("email taken");
    }
    student.setEmail(email);
    student.setName(name);
    studentRepository.save(student);
  }

  @Override
  public void assignCourseToStudent(Long studentId, Long courseId) {
    Student student =
        studentRepository
            .findById(studentId)
            .orElseThrow(
                () -> new StudentNotFoundException("student not found with id: " + studentId));

    if (studentRepository.countByAssignedCourses_courseId(courseId) < 4) {
      Course course = courseServiceImpl.getCourseById(courseId);
      student.getAssignedCourses().add(course);
      studentRepository.save(student);
    } else {
      throw new SMSException("fazla kişi");
    }
  }

  @Override
  public Long getNumberOfStudentsEnrolledInCourse(Long courseId) {
    return studentRepository.countByAssignedCourses_courseId(courseId);
  }

  @Override
  public void deleteStudentFromCourse(Long studentId, Long courseId) {
    Student student =
        studentRepository
            .findById(studentId)
            .orElseThrow(
                () -> new StudentNotFoundException("student not found with id: " + studentId));
    Course course = courseServiceImpl.getCourseById(courseId);
    student.getAssignedCourses().remove(course);
    studentRepository.save(student);
  }

  @Override
  public List<Student> getStudentsByCourse(Long courseId) {
    Optional<List<Student>> students =
        studentRepository.findStudentsByAssignedCourses_courseId(courseId);
    return students.orElse(List.of());
  }
}
