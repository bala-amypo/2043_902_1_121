package com.example.demo.controller;

import com.example.demo.model.Student;
import com.example.demo.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Student> add(@Valid @RequestBody Student s) {
        // Return 201 Created for test consistency
        return new ResponseEntity<>(service.addStudent(s), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Student>> getAll() {
        return ResponseEntity.ok(service.getAllStudents());
    }

    // REQUIRED by test suite - mapping to /api/students/list
    @GetMapping("/list")
    public ResponseEntity<List<Student>> list() {
        return ResponseEntity.ok(service.getAllStudents());
    }
}