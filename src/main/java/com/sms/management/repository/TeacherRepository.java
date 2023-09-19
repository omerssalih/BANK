package com.sms.management.repository;

import com.sms.management.entity.Student;
import com.sms.management.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {

  Optional<Teacher> findByTeacherCode(int teacherCode);

  Optional<List<Teacher>> findTeachersByAssignedTeacherCourses_courseId(Long courseId);
}
