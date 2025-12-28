package com.example.demo.service.impl;

import com.example.demo.exception.ApiException;
import com.example.demo.model.ExamSession;
import com.example.demo.model.Student;
import com.example.demo.repository.ExamSessionRepository;
import com.example.demo.repository.StudentRepository;
import com.example.demo.service.ExamSessionService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Service
public class ExamSessionServiceImpl implements ExamSessionService {

    private final ExamSessionRepository sessionRepo;
    private final StudentRepository studentRepo;

    // 🔑 REQUIRED by test suite (DO NOT CHANGE)
    public ExamSessionServiceImpl(
            ExamSessionRepository sessionRepo,
            StudentRepository studentRepo
    ) {
        this.sessionRepo = sessionRepo;
        this.studentRepo = studentRepo;
    }

    @Override
    public ExamSession createSession(ExamSession session) {

        // null session
        if (session == null) {
            throw new ApiException("Session details are incomplete");
        }

        // exam date validation (test06)
        if (session.getExamDate() == null ||
                session.getExamDate().isBefore(LocalDate.now())) {
            throw new ApiException("Session date cannot be in the past");
        }

        // students required (test38)
        Set<Student> students = session.getStudents();
        if (students == null || students.isEmpty()) {
            throw new ApiException("Students are required");
        }

        return sessionRepo.save(session);
    }

    @Override
    public ExamSession getSession(Long id) {
        return sessionRepo.findById(id)
                .orElseThrow(() -> new ApiException("Session not found"));
    }

    @Override
    public List<ExamSession> getSessionsByDate(LocalDate date) {
        return sessionRepo.findByExamDate(date);
    }

    // 🔑 REQUIRED BY INTERFACE
    @Override
    public List<ExamSession> getAllSessions() {
        return sessionRepo.findAll();
    }
}
