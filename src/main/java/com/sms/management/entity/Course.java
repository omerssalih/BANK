package com.sms.management.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Course implements Serializable {

  @Id
  @SequenceGenerator(name = "course_sequence", sequenceName = "course_sequence", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "course_sequence")
  private Long courseId;

  private String courseName;
  private int courseCode;

  @JsonBackReference
  @ManyToMany(mappedBy = "assignedCourses")
  private Set<Student> students = new HashSet<>();

  @JsonBackReference
  @ManyToMany(mappedBy = "assignedTeacherCourses")
  private Set<Teacher> teachers = new HashSet<>();
}
