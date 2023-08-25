package com.sms.management.lesson;

import com.sms.management.student.Student;
import jakarta.persistence.*;

@Entity
public class Lesson {
    @Id
    @SequenceGenerator(name = "lesson_sequence",
            sequenceName = "lesson_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "lesson_sequence")

    private Long id;

    /*@ManyToMany
    private Student student;*/
}
