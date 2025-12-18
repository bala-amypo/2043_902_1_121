package com.example.demo.repository;

import com.example.demo.model.ExamSession;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ExamSessionRepository extends JpaRepository<ExamSession, Long> {

    ExamSession findByExamDate(LocalDate examDate);
}
