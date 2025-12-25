package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.model.ExamSession;
import java.time.LocalDate;

public interface ExamSessionRepository extends JpaRepository<ExamSession, Long> {
    ExamSession findByExamDate(LocalDate date);
}
