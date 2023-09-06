package com.sms.management.service;
import com.sms.management.entity.Course;
import com.sms.management.exception.StudentNotFoundException;
import com.sms.management.repository.TeacherRepository;
import com.sms.management.entity.Teacher;
import jakarta.persistence.Entity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TeacherService {

    private final TeacherRepository teacherRepository;
    private final CourseService courseService;

    public void addNewTeacher(Teacher teacher) {
        teacherRepository.save(teacher);
    }


    public List<Teacher> getTeachers(){
        return teacherRepository.findAll();
    }

    public void deleteTeacher(Long teacherId) {
        boolean exits = teacherRepository.existsById(teacherId);
        if(!exits){
            throw new IllegalStateException(
                    "teacher with id" + teacherId + "does not exists");
        }
        teacherRepository.deleteById(teacherId);
    }

    public void updateTeacher(Long teacherId, String teacherName, String teacherCourse) {
        Optional<Teacher> optionalTeacher = teacherRepository.findById(teacherId);
        if(optionalTeacher.isPresent()){
            Teacher teacher = optionalTeacher.get();
            teacher.setTeacherName(teacherName);
            teacher.setTeacherCourse(teacherCourse);
            teacherRepository.save(teacher);
        }
        else {
            throw new IllegalStateException(
                    "teacher with name" + teacherName + "does not exists");
        }
    }

    public void assignCourseToTeacher(Long teacherId, Long courseId) {
        Teacher teacher = teacherRepository.findById(teacherId).orElseThrow(() -> new StudentNotFoundException(
                "teacher not found with id: " + teacherId));
        Course course = courseService.getCourseById(courseId);
        teacher.getAssignedTeacherCourses().add(course);
        teacherRepository.save(teacher);
    }
}
