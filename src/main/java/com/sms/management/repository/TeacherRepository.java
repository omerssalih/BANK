package com.sms.management.repository;

import com.sms.management.entity.Student;
import com.sms.management.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeacherRepository
        extends JpaRepository<Teacher, Long> {

    @Query("SELECT s FROM Teacher s WHERE s.teacherId = ?1")
    Optional<Teacher> findByName(Long teachreId);
}
