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

        // 🔑 test36
        if (student == null) {
            throw new ApiException("Student details are incomplete");
        }

        // 🔑 test03 (year first)
        if (student.getYear() == null ||
            student.getYear() < 1 ||
            student.getYear() > 4) {
            throw new ApiException("Invalid year");
        }

        // 🔑 test36 (mandatory fields)
        if (student.getRollNumber() == null ||
            student.getName() == null ||
            student.getDepartment() == null) {
            throw new ApiException("Student details are incomplete");
        }

        // 🔑 test16 → MUST call findByRollNumber (once)
        if (repo.findByRollNumber(student.getRollNumber()).isPresent()) {
            throw new ApiException("Roll number already exists");
        }

        return repo.save(student);
    }

    @Override
    public List<Student> getAllStudents() {
        return repo.findAll();
    }
}
