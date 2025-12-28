package com.example.demo.repository;

import com.example.demo.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {

    // 🔑 REQUIRED by test16 (must be used)
    Optional<Student> findByRollNumber(String rollNumber);
}
