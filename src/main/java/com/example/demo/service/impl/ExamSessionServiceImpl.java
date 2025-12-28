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

    // 🔑 REQUIRED BY TEST SUITE (DO NOT CHANGE)
    public ExamSessionServiceImpl(
            ExamSessionRepository sessionRepo,
            StudentRepository studentRepo
    ) {
        this.sessionRepo = sessionRepo;
        this.studentRepo = studentRepo;
    }

    @Override
    public ExamSession createSession(ExamSession session) {

        // 🔑 null check
        if (session == null) {
            throw new ApiException("Session details are incomplete");
        }

        // 🔑 past date validation (test06)
        if (session.getDate() == null || session.getDate().isBefore(LocalDate.now())) {
            throw new ApiException("Session date must be in the future");
        }

        // 🔑 students required (test38)
        Set<Student> students = session.getStudents();
        if (students == null || students.isEmpty()) {
            throw new ApiException("Session must have students");
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
        return sessionRepo.findByDate(date);
    }
}
