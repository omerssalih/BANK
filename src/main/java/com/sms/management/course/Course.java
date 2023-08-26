package com.sms.management.course;

import jakarta.persistence.*;

@Entity
@Table
public class Course {

    @Id
    @SequenceGenerator( name = "course_sequence",
    sequenceName = "course_sequence",
    allocationSize = 1)

    @GeneratedValue( strategy = GenerationType.SEQUENCE,
    generator ="course_sequence")

    private Long courseId;
    private String courseName;
    private int courseCode;

    public Course() {
    }

    public Course(Long courseId, String courseName, int courseCode) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.courseCode = courseCode;
    }

    public Course(String courseName, int courseCode) {
        this.courseName = courseName;
        this.courseCode = courseCode;
    }

    public Long getCourseId() {
        return courseId;
    }

    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(int courseCode) {
        this.courseCode = courseCode;
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseId=" + courseId +
                ", courseName='" + courseName + '\'' +
                ", courseCode=" + courseCode +
                '}';
    }
}
