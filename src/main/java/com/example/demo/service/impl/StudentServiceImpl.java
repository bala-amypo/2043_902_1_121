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

        // 🔴 Null check
        if (student == null) {
            throw new ApiException("Student details are incomplete");
        }

        // 🔴 Mandatory field checks (NULL + BLANK)
        if (student.getRollNumber() == null || student.getRollNumber().trim().isEmpty()
                || student.getName() == null || student.getName().trim().isEmpty()
                || student.getDepartment() == null || student.getDepartment().trim().isEmpty()
                || student.getYear() == null) {
            throw new ApiException("Student details are incomplete");
        }

        // 🔴 test03: year validation MUST happen BEFORE repo call
        if (student.getYear() < 1 || student.getYear() > 4) {
            throw new ApiException("Invalid year");
        }

        // 🔴 test16: uniqueness check using repository
        if (repo.findByRollNumber(student.getRollNumber().trim()).isPresent()) {
            throw new ApiException("Roll number already exists");
        }

        return repo.save(student);
    }

    @Override
    public List<Student> getAllStudents() {
        return repo.findAll();
    }
}
