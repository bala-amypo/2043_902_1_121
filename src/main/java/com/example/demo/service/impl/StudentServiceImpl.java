package com.example.demo.service.impl;

import com.example.demo.exception.ApiException;
import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;
import com.example.demo.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student addStudent(Student student) {

        if (student.getRollNumber() == null || student.getYear() == null) {
            throw new ApiException("invalid student");
        }

        if (student.getYear() < 1 || student.getYear() > 5) {
            throw new ApiException("invalid year");
        }

        if (studentRepository.findByRollNumber(student.getRollNumber()).isPresent()) {
            throw new ApiException("roll number already exists");
        }

        return studentRepository.save(student);
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }
}



package com.example.demo.service.impl;

import com.example.demo.exception.ApiException;
import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;
import com.example.demo.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student addStudent(Student student) {

        if (student.getRollNumber() == null || student.getYear() == null) {
            throw new ApiException("invalid student");
        }

        if (student.getYear() < 1 || student.getYear() > 5) {
            throw new ApiException("invalid year");
        }

        if (studentRepository.findByRollNumber(student.getRollNumber()).isPresent()) {
            throw new ApiException("roll number already exists");
        }

        return studentRepository.save(student);
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }
    
    //other operations
    @Override
    public Student getStudentById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new ApiException("student not found"));
    }

    @Override
    public Student updateStudent(Long id, Student updatedStudent) {

        Student existing = studentRepository.findById(id)
                .orElseThrow(() -> new ApiException("student not found"));

        // prevent duplicate rollNumber
        studentRepository.findByRollNumber(updatedStudent.getRollNumber())
                .filter(s -> !s.getId().equals(id)) // ignore same student
                .ifPresent(s -> {
                    throw new ApiException("roll number already exists");
                });

        existing.setName(updatedStudent.getName());
        existing.setRollNumber(updatedStudent.getRollNumber());
        existing.setDepartment(updatedStudent.getDepartment());
        existing.setYear(updatedStudent.getYear());

        return studentRepository.save(existing);
    }

    @Override
    public void deleteStudent(Long id) {

        if (!studentRepository.existsById(id)) {
            throw new ApiException("student not found");
        }

        studentRepository.deleteById(id);
    }
}
