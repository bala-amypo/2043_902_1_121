package com.example.demo.service.impl;

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
            StudentRepository studentRepo
    ) {
        this.repo = repo;
        this.studentRepo = studentRepo;
    }

    @Override
    public ExamSession createSession(ExamSession session) {

        if (session == null) {
            throw new IllegalArgumentException("Session cannot be null");
        }

        if (session.getExamDate() == null ||
                session.getExamDate().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Exam date cannot be in the past");
        }

        if (session.getStudents() == null || session.getStudents().isEmpty()) {
            throw new IllegalArgumentException("Session must have at least 1 student");
        }

        return repo.save(session);
    }

    @Override
    public ExamSession getSession(Long id) {
        return repo.findById(id).orElse(null);
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
