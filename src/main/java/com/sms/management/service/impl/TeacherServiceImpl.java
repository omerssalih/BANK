package com.sms.management.service.impl;

import com.sms.management.dto.*;
import com.sms.management.entity.Course;
import com.sms.management.entity.Teacher;
import com.sms.management.exception.*;
import com.sms.management.repository.CourseRepository;
import com.sms.management.repository.TeacherRepository;
import com.sms.management.service.CourseService;
import com.sms.management.service.TeacherService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class TeacherServiceImpl implements TeacherService {

  private final TeacherRepository teacherRepository;
  private final CourseRepository courseRepository;
  private final CourseService courseService;
  private final ModelMapper modelMapper;
  private final Environment environment;

  @Override
  public void addNewTeacher(CreateTeacherDto createTeacherDto) {
    //String[] activeProfiles = environment.getActiveProfiles();
    Optional<Teacher> teacherOptional =
        teacherRepository.findByTeacherCode(createTeacherDto.getTeacherCode());
    if (teacherOptional.isPresent()) {
      throw new IllegalStateException("the code is taken");
    } else {
      teacherRepository.save(modelMapper.map(createTeacherDto, Teacher.class));
    }
  }

  @Override
  @Cacheable(value = "Teachers")
  public List<GetTeachersDto> getTeachers() {
    log.info("Get teacher methodu redis ile çalıştı.");
    List<Teacher> teachers = teacherRepository.findAll();
    List<GetTeachersDto> result = new ArrayList<>();
    teachers.forEach(
        teacher -> {
          GetTeachersDto getTeachersDto = modelMapper.map(teacher, GetTeachersDto.class);
          result.add(getTeachersDto);
        });
    return result;
  }

  @Override
  public void deleteTeacher(Long teacherId) {
    boolean exits = teacherRepository.existsById(teacherId);
    if (!exits) {
      throw new IllegalStateException("teacher with id" + teacherId + "does not exists");
    }
    teacherRepository.deleteById(teacherId);
  }

  @Override
  public void deleteTeacherFromCourse(Long teacherId, Long courseId) {
    Teacher teacher =
        teacherRepository
            .findById(teacherId)
            .orElseThrow(
                () ->
                    new TeacherNotFoundException(
                        "teacher not found with this id: " + teacherId));
    Course course = courseService.getCourseById(courseId);
    teacher.getAssignedTeacherCourses().remove(course);
    teacherRepository.save(teacher);
  }

  @Override
  public void updateTeacher(UpdateTeacherDto teacherDto) {
    Optional<Teacher> optionalTeacher =
        teacherRepository.findByTeacherCode(teacherDto.getTeacherCode());
    Course course = courseService.getCourseById(teacherDto.getCourseId());
    if (optionalTeacher.isPresent()) {
      if (optionalTeacher.get().getTeacherPassword() == teacherDto.getInputPassword()) {
        Teacher  teacher = optionalTeacher.get();
        teacher.setTeacherName(teacherDto.getTeacherName());
        teacher.getAssignedTeacherCourses().add(course);
        teacherRepository.save(teacher);
      } else {
        throw new AuthenticationException("Şifre Hatalı");
      }
    } else {
      throw new StudentNotFoundException(
          "teacher with this code" + teacherDto.getTeacherCode() + "does not exists");
    }
  }

  @Override
  public void assignCourseToTeacher(Long teacherId, Long courseId) {
    Teacher teacher =
        teacherRepository
            .findById(teacherId)
            .orElseThrow(
                () -> new StudentNotFoundException("teacher not found with id: " + teacherId));
    Course course =
        courseRepository
            .findCourseByCourseId(courseId)
            .orElseThrow(
                () -> new CourseNotFoundException("course not found with id: " + courseId));
    courseService.getCourseById(courseId);
    teacher.getAssignedTeacherCourses().add(course);
    teacherRepository.save(teacher);
  }

  @Override
  public List<Teacher> getTeacherByCourse(Long courseId) {
    Optional<List<Teacher>> teachers =
        teacherRepository.findTeachersByAssignedTeacherCourses_courseId(courseId);
    return teachers.get();
  }
}
