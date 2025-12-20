package com.example.demo.controller;

import com.example.demo.model.Student;
import com.example.demo.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    @Operation(summary = "Add student")
    public Student add(@RequestBody Student student) {
        return studentService.addStudent(student);
    }

    @GetMapping
    @Operation(summary = "List students")
    public List<Student> list() {
        return studentService.getAllStudents();
    }
    

    //other operations
    @GetMapping("/{id}")
    @Operation(summary = "Get student by ID")
    public Student get(@PathVariable Long id) {
        return studentService.getStudentById(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update student")
    public Student update(@PathVariable Long id, @RequestBody Student student) {
        return studentService.updateStudent(id, student);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete student")
    public String delete(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return "deleted";
    }
}
