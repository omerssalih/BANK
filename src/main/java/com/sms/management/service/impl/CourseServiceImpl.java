package com.sms.management.service.impl;

import com.sms.management.dto.CreateCourseDto;
import com.sms.management.entity.Course;
import com.sms.management.exception.CourseNotFoundException;
import com.sms.management.repository.CourseRepository;
import java.util.List;
import java.util.Optional;

import com.sms.management.service.CourseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CourseServiceImpl implements CourseService {
  private final CourseRepository courseRepository;
  private final ModelMapper modelMapper;

  @Cacheable(value = "courses")
  public List<Course> getCourses() {
    log.info("Get teacher methodu redis ile çalıştı.");
    return courseRepository.findAll();
  }

  public void deleteCourse(Long courseId) {
    boolean exists = courseRepository.existsById(courseId);
    if (!exists) {
      throw new CourseNotFoundException("course with id: " + courseId + " does not exists");
    }
    courseRepository.deleteById(courseId);
  }

  public void addNewCourse(CreateCourseDto createCourseDto) {
    Optional<Course> courseOptional =
        courseRepository.findCourseByCourseCode(createCourseDto.getCourseCode());
    if (courseOptional.isPresent()) {
      throw new IllegalStateException("course exist");
    } else {
      Course course = modelMapper.map(createCourseDto, Course.class);
      courseRepository.save(course);
    }
  }

  public Course getCourseById(Long courseId) {
    return courseRepository
        .findById(courseId)
        .orElseThrow(() -> new CourseNotFoundException("course not found with id: " + courseId));
  }

  public void updateCourse(Long courseId, String courseName, int courseCode) {
    Optional<Course> optionalCourse = courseRepository.findById(courseId);
    if (optionalCourse.isPresent()) {
      Course course = optionalCourse.get();
      course.setCourseName(courseName);
      course.setCourseCode(courseCode);
      courseRepository.save(course); // Veritabanına güncellemeyi kaydet
    } else {
      throw new CourseNotFoundException(
          "course not found with id: "
              + courseId); // Kurs bulunamadı durumunda
    }
  }



}
