package com.sms.management.repository;
import com.sms.management.entity.Course;
import com.sms.management.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CourseRepository
        extends JpaRepository<Course, Long> {

    @Query("SELECT s FROM Course s WHERE s.courseCode = ?1")
    Optional<Course> findCoursesByCourseCode(int courseCode);
}
