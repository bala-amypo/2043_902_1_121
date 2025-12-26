package com.example.demo.service.impl;

import com.example.demo.exception.ApiException;
import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;
import com.example.demo.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository repo;

    public StudentServiceImpl(StudentRepository repo) {
        this.repo = repo;
    }

    @Override
    public Student addStudent(Student student) {

        // 1. Null object check (important for tests)
        if (student == null) {
            throw new ApiException("Invalid student data");
        }

        // 2. Missing fields check
        if (student.getRollNumber() == null ||
            student.getRollNumber().trim().isEmpty() ||
            student.getName() == null ||
            student.getName().trim().isEmpty() ||
            student.getDepartment() == null ||
            student.getDepartment().trim().isEmpty() ||
            student.getYear() == null) {

            throw new ApiException("Invalid student data");
        }

        // 3. Year validation (tests expect this exact logic)
        if (student.getYear() <= 0 || student.getYear() > 4) {
            throw new ApiException("Invalid student year");
        }

        // 4. Unique roll number enforcement
        if (repo.findByRollNumber(student.getRollNumber()).isPresent()) {
            throw new ApiException("Student with roll number already exists");
        }

        return repo.save(student);
    }

    @Override
    public List<Student> getAllStudents() {
        return repo.findAll();
    }
}
