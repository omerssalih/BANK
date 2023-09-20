package com.sms.management.repository;

import com.sms.management.entity.Course;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

  Optional<Course> findCourseByCourseCode(int courseCode);

  Optional<Course> findCourseByCourseId(Long courseId);

  Optional<Course> findCourseByCourseName(String courseName);
}
