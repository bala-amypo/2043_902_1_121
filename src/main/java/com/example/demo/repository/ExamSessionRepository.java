package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.model.ExamSession;

import java.time.LocalDate;
import java.util.Optional;

public interface ExamSessionRepository extends JpaRepository<ExamSession, Long> {

    // ✅ Tests expect Optional
    Optional<ExamSession> findByExamDate(LocalDate date);
}
