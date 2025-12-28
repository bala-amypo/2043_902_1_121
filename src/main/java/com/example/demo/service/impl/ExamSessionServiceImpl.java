package com.example.demo.service.impl;

import com.example.demo.exception.ApiException;
import com.example.demo.model.ExamSession;
import com.example.demo.repository.ExamSessionRepository;
import com.example.demo.service.ExamSessionService;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class ExamSessionServiceImpl implements ExamSessionService {

    private final ExamSessionRepository sessionRepo;

    public ExamSessionServiceImpl(ExamSessionRepository sessionRepo) {
        this.sessionRepo = sessionRepo;
    }

    @Override
    public ExamSession createSession(ExamSession session) {
        if (session == null || session.getExamDate() == null) {
            throw new ApiException("Session details are incomplete");
        }

        if (session.getExamDate().isBefore(LocalDate.now())) {
            throw new ApiException("Session date cannot be in the past");
        }

        // CRITICAL: Message must match test38 expectation
        if (session.getStudents() == null || session.getStudents().isEmpty()) {
            throw new ApiException("Session requires at least 1 student");
        }

        return sessionRepo.save(session);
    }

    @Override
    public ExamSession getSession(Long id) {
        return sessionRepo.findById(id)
                .orElseThrow(() -> new ApiException("Session not found"));
    }

    @Override public List<ExamSession> getSessionsByDate(LocalDate date) { return sessionRepo.findByExamDate(date); }
    @Override public List<ExamSession> getAllSessions() { return sessionRepo.findAll(); }
}