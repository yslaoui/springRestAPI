package com.example.rest.controller;

import com.example.rest.entity.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        if (studentId < 0 || studentId >= this.students.size()) {
            throw new StudentNotFoundException("Student id not found - " + studentId);
        }
        return this.students.get(studentId);
    }

    @GetMapping("/tryingResponseEntity")
        public ResponseEntity<List<Student>>  getCustomReponse() {
            HttpHeaders header = new HttpHeaders();
            header.add("first", "customResponse");
            return new ResponseEntity<>(this.students, header, HttpStatus.FAILED_DEPENDENCY);
    }

    @ExceptionHandler
    public ResponseEntity<StudentCustomError> handleException(StudentNotFoundException exc) {
        StudentCustomError studentCustomError = new StudentCustomError();
        studentCustomError.setStatus(HttpStatus.NOT_FOUND.value());
        studentCustomError.setMessage(exc.getMessage());
        studentCustomError.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<StudentCustomError>(studentCustomError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<StudentCustomError> handleException(Exception exc) {
        StudentCustomError studentCustomError = new StudentCustomError();
        studentCustomError.setStatus(HttpStatus.BAD_REQUEST.value());
        studentCustomError.setMessage(exc.getMessage());
        studentCustomError.setTimeStamp(System.currentTimeMillis());
        return new ResponseEntity<StudentCustomError>(studentCustomError, HttpStatus.BAD_REQUEST);
    }
}





