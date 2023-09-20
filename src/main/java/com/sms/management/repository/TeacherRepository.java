package com.sms.management.repository;

import com.sms.management.entity.Teacher;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {

  Optional<Teacher> findByTeacherCode(int teacherCode);

  Optional<List<Teacher>> findTeachersByAssignedTeacherCourses_courseId(Long courseId);

  Long countByAssignedTeacherCourses_courseId(Long courseId);
}
