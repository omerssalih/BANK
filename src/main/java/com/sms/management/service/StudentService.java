package com.sms.management.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sms.management.dto.CreateStudentDto;
import com.sms.management.dto.GetStudentsDto;
import com.sms.management.entity.Course;
import com.sms.management.entity.Student;
import com.sms.management.exception.StudentNotFoundException;
import com.sms.management.repository.StudentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final CourseService courseService;
    private final CacheService cacheService;
    private final ModelMapper modelMapper;

    @Cacheable(value = "students")
    public List<GetStudentsDto> getStudents() {
        /*cacheService.addToCache("getStudents", "students", 20);
        String cachedValue = cacheService.getFromCache("getStudents");*/
        log.info("get students methodu redis ile çalıştı" );
        List<Student> students = studentRepository.findAll();
        List<GetStudentsDto> result = new ArrayList<>();
        students.forEach(student -> {
            LocalDate dob = student.getDob();
            LocalDate now = LocalDate.now();
            int age = Period.between(dob, now).getYears();
            GetStudentsDto getStudentDto = modelMapper.map(student, GetStudentsDto.class);
            getStudentDto.setAge(age);
            result.add(getStudentDto);
        });
        return result;
    }

    @Cacheable(value = "students", key="#studentId")
    public List<Student> getStudentsById(Long studentId) {
        log.info("redis deneme" + studentId);
        return studentRepository.findAllById(Collections.singleton(studentId));
    }


    public void addNewStudent(CreateStudentDto createStudentDto) {
        Optional<Student> studentOptional = studentRepository
                .findStudentByEmail(createStudentDto.getEmail());
        if(studentOptional.isPresent()){
            throw new IllegalStateException("email taken");
        }
        Student student = modelMapper.map(createStudentDto, Student.class);
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
    @CachePut(value = "students", key = "#studentId")
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

    public void deleteStudentFromCourse(Long studentId, Long courseId) {
        Student student = studentRepository.findById(studentId).orElseThrow(() -> new StudentNotFoundException(
                "student not found with id: " + studentId));
        Course course = courseService.getCourseById(courseId);
        student.getAssignedCourses().remove(course);
        studentRepository.save(student);
    }

    public List<Student> getStudentsByCourse(Long courseId) {
        Optional<List<Student>> students = studentRepository.findStudentsByAssignedCourses_courseId(courseId);
        return students.get();
    }
}
