package com.sms.management.repository;
import com.sms.management.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CourseRepository
        extends JpaRepository<Course, Long> {


    Optional<Course> findCourseByCourseCode(int courseCode);
}
