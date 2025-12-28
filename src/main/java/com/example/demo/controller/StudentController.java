package com.example.demo.controller;

import com.example.demo.model.Student;
import com.example.demo.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Student> add(@Valid @RequestBody Student s) {
        return ResponseEntity.ok(service.addStudent(s));
    }

    // ✅ Existing REST endpoint (keep)
    @GetMapping
    public ResponseEntity<List<Student>> getAll() {
        return ResponseEntity.ok(service.getAllStudents());
    }

    // ✅ REQUIRED by test suite
    public ResponseEntity<List<Student>> list() {
        return ResponseEntity.ok(service.getAllStudents());
    }
}
