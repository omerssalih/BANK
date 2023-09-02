package com.sms.management.controller;

import com.sms.management.entity.Course;
import com.sms.management.repository.CourseRepository;
import com.sms.management.repository.StudentRepository;
import com.sms.management.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/course")
public class CourseController {
    private final CourseService courseService;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    public CourseController(CourseService courseService){
        this.courseService = courseService;
    }

    @GetMapping
    public List<Course> getCourses(){
        return courseService.getCourses();
    }

    @GetMapping(path = "courseId")
    public List<Course> getCoursesById(Long courseId){

        return courseService.getCoursesById(courseId);
    }


    @DeleteMapping(path = "{courseId}")
    public void deleteCourse(@PathVariable("courseId") Long courseId){

        courseService.deleteCourse(courseId);
    }

    @PostMapping
    public void registerNewCourse(@RequestBody Course course){

        courseService.addNewCourse(course);
    }

    @PutMapping
    public void updateCourse(@RequestBody Course course){
        courseService.updateCourse(course.getCourseId(), course.getCourseName(), course.getCourseCode());
    }

    @DeleteMapping("/{courseId}/students/{studentId}")
    public ResponseEntity<String> removeStudentFromCourse(@PathVariable Long courseId, @PathVariable Long studentId) {
        StudentRepository.deleteStudentFromCourse(studentId);
        return ResponseEntity.ok("Öğrenci başarıyla kursdan silindi.");
    }
}
