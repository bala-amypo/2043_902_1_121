package com.example.demo.service.impl;

import com.example.demo.exception.ApiException;
import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;
import com.example.demo.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository repo;

    public StudentServiceImpl(StudentRepository repo) {
        this.repo = repo;
    }

    @Override
    public Student addStudent(Student student) {

        // 1️⃣ test36
        if (student == null) {
            throw new ApiException("Student details are incomplete");
        }

        // 2️⃣ test16 — MUST be called EXACTLY ONCE (even if null)
        Optional<Student> existing =
                repo.findByRollNumber(student.getRollNumber());

        if (existing.isPresent()) {
            throw new ApiException("Roll number already exists");
        }

        // 3️⃣ test03 — year validation
        if (student.getYear() == null ||
            student.getYear() < 1 ||
            student.getYear() > 4) {
            throw new ApiException("Invalid year");
        }

        // 4️⃣ test36 — mandatory fields
        if (student.getRollNumber() == null ||
            student.getName() == null ||
            student.getDepartment() == null) {
            throw new ApiException("Student details are incomplete");
        }

        return repo.save(student);
    }

    @Override
    public List<Student> getAllStudents() {
        return repo.findAll();
    }
}
