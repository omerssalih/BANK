package com.sms.management.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Teacher implements Serializable {

  @Getter
  @Id
  @SequenceGenerator(
      name = "teacher_sequence",
      sequenceName = "teacher_sequence",
      allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "teacher_sequence")
  private Long teacherId;

  @Size(min = 2, max = 100)
  @NotNull
  @NotEmpty
  private String teacherName;

  @NotNull private int teacherCode;
  @NotNull private int teacherPassword;


  @JsonManagedReference
  @ManyToMany
  @JoinTable(
      name = "teacher_course",
      joinColumns = @JoinColumn(name = "teacher_id"),
      inverseJoinColumns = @JoinColumn(name = "course_id"))
  private Set<Course> assignedTeacherCourses = new HashSet<>();
}
