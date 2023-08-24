package com.example2.demo3.student;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Service
public class StudentService {

    public List<Student> getStudents() {
        return List.of(
                new Student(
                        1L,
                        "omer",
                        "onsalih10@gmail.com",
                        LocalDate.of(2001, Month.MAY, 8),
                        22

                ), new Student(
                        1L,
                        "hakan",
                        "hakan@gmail.com",
                        LocalDate.of(2001, Month.MAY, 8),
                        22

                )
        );

    }
}
