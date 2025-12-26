package com.example.demo.service.impl;

import com.example.demo.exception.ApiException;
import com.example.demo.model.ExamSession;
import com.example.demo.repository.ExamSessionRepository;
import com.example.demo.repository.StudentRepository;
import com.example.demo.service.ExamSessionService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ExamSessionServiceImpl implements ExamSessionService {

    private final ExamSessionRepository repo;
    private final StudentRepository studentRepo;

    public ExamSessionServiceImpl(
            ExamSessionRepository repo,
            StudentRepository studentRepo) {
        this.repo = repo;
        this.studentRepo = studentRepo;
    }

    @Override
    public ExamSession createSession(ExamSession session) {

        // 1. Null check
        if (session == null) {
            throw new ApiException("Invalid exam session");
        }

        // 2. Exam date validation
        if (session.getExamDate() == null ||
            !session.getExamDate().isAfter(LocalDate.now())) {
            // past OR today is invalid as per tests
            throw new ApiException("Invalid exam date");
        }

        // 3. Students must be present
        if (session.getStudents() == null ||
            session.getStudents().isEmpty()) {
            throw new ApiException("Session must have students");
        }

        return repo.save(session);
    }

    @Override
    public ExamSession getSession(Long id) {

        if (id == null) {
            throw new ApiException("Session not found");
        }

        return repo.findById(id)
                .orElseThrow(() -> new ApiException("Session not found"));
    }

    @Override
    public List<ExamSession> getSessionsByDate(LocalDate date) {
        return repo.findByExamDate(date);
    }

    @Override
    public List<ExamSession> getAllSessions() {
        return repo.findAll();
    }
}
