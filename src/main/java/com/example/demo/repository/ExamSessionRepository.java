package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.model.ExamSession;

import java.time.LocalDate;
import java.util.List;

public interface ExamSessionRepository extends JpaRepository<ExamSession, Long> {

    // ✅ MUST return List (tests mock List)
    List<ExamSession> findByExamDate(LocalDate date);
}
