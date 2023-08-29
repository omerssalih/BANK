package com.sms.management.service;

import com.sms.management.entity.Student;
import com.sms.management.exception.CourseNotFoundException;
import com.sms.management.repository.CourseRepository;
import com.sms.management.entity.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {


    private final CourseRepository courseRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository){

        this.courseRepository = courseRepository;
    }

    public List<Course> getCourses(){
        return courseRepository.findAll();
    }

    public void deleteCourse(Long courseId) {
        boolean exists = courseRepository.existsById(courseId);
        if(!exists){
            throw new IllegalStateException(
                    "course with id" + courseId + "does not exists");
        }
        courseRepository.deleteById(courseId);
    }

    public void addNewCourse(Course course) {
        Optional<Course> courseOptional = courseRepository
                .findCoursesByCourseCode(course.getCourseCode());
        if(courseOptional.isPresent()){
            throw new IllegalStateException("course exist");
        }
        courseRepository.save(course);
    }

    public Course getCourseById(Long courseId){
        return courseRepository.findById(courseId).orElseThrow(() -> new CourseNotFoundException(
                 "course not found with id: " + courseId));
    }
}
