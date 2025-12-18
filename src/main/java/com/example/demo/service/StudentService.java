// public class StudentService{

// }
package com.example.demo.service;

import com.example.demo.exception.ApiException;
import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student addStudent(Student student) {

        if (student.getYear() < 1 || student.getYear() > 5) {
            throw new ApiException("year invalid");
        }

        if (studentRepository.findByRollNumber(student.getRollNumber()) != null) {
            throw new ApiException("exists");
        }

        return studentRepository.save(student);
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }
}
