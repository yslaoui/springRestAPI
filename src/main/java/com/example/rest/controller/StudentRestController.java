package com.example.rest.controller;

import com.example.rest.entity.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {
    List<Student> students;

    @PostConstruct
    public void load() {
        this.students  = new ArrayList<>();
        this.students.add(new Student("Maria", "Fitoussi"));
        this.students.add(new Student("Anna", "kamel"));
        this.students.add(new Student("Kamala", "Trump"));

    }

    @GetMapping("/students")
    public List<Student> getStudents() {
        this.load();
        return this.students;
    }

    @GetMapping("/students/{studentId}")
    public Student getStudent(@PathVariable int studentId) {
        return this.students.get(studentId);
    }
}
