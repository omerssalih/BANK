package com.sms.management.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Teacher {

    @Getter
    @Id
    private Long teacherId;
    private String teacherName;
    private String teacherCourse;

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    @JsonManagedReference
    @ManyToMany
    @JoinTable(name = "teacher_course",
            joinColumns = @JoinColumn(name = "teacher_id"),
            inverseJoinColumns = @JoinColumn(name ="course_id" )
    )
    private Set<Course> assignedTeacherCourses = new HashSet<>();
}
