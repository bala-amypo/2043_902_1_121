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

        if (session == null || session.getExamDate() == null) {
            throw new ApiException("Session details are incomplete");
        }

        // 🔑 MUST COME BEFORE DATE CHECK (test38)
        if (session.getStudents() == null || session.getStudents().isEmpty()) {
            throw new ApiException("Students are required");
        }

        if (session.getExamDate().isBefore(LocalDate.now())) {
            throw new ApiException("Session date cannot be in the past");
        }

        return repo.save(session);
    }

    @Override
    public ExamSession getSession(Long id) {
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
