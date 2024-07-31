package com.example.rest.controller;

import com.example.rest.entity.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {

    @GetMapping("/students")
    public List<Student> getStudents() {
        List<Student> students = new ArrayList<>();
        students.add(new Student("Maria", "Fitoussi"));
        students.add(new Student("Anna", "kamel"));
        students.add(new Student("Kamala", "Trump"));
        return students;
    }
}
