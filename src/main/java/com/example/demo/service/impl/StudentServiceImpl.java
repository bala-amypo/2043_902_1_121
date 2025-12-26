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

        if (student == null ||
            student.getRollNumber() == null ||
            student.getName() == null ||
            student.getDepartment() == null ||
            student.getYear() == null) {
            throw new ApiException("Student details are incomplete");
        }

        if (student.getYear() < 1 || student.getYear() > 4) {
            throw new ApiException("Invalid year");
        }

        Optional<Student> existing =
                repo.findByRollNumber(student.getRollNumber());

        if (existing.isPresent()) {
            throw new ApiException("Student with this roll number already exists");
        }

        return repo.save(student);
    }

    @Override
    public List<Student> getAllStudents() {
        return repo.findAll();
    }
}
