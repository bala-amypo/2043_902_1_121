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

    private final ExamSessionRepository examSessionRepository;
    private final StudentRepository studentRepository;

    public ExamSessionServiceImpl(ExamSessionRepository examSessionRepository,
                                  StudentRepository studentRepository) {
        this.examSessionRepository = examSessionRepository;
        this.studentRepository = studentRepository;
    }

    // CREATE -------------------------------------------------------
    @Override
    public ExamSession createSession(ExamSession session) {

        if (session.getExamDate().isBefore(LocalDate.now())) {
            throw new ApiException("exam date in the past");
        }

        if (session.getStudents() == null || session.getStudents().isEmpty()) {
            throw new ApiException("at least 1 student required");
        }

        return examSessionRepository.save(session);
    }

    // READ ---------------------------------------------------------
    @Override
    public ExamSession getSession(Long sessionId) {
        return examSessionRepository.findById(sessionId)
                .orElseThrow(() -> new ApiException("session not found"));
    }

    @Override
    public List<ExamSession> getAllSessions() {
        return examSessionRepository.findAll();
    }

    // UPDATE -------------------------------------------------------
    @Override
    public ExamSession updateSession(Long sessionId, ExamSession updated) {
        ExamSession existing = getSession(sessionId);

        if (updated.getExamName() != null)
            existing.setExamName(updated.getExamName());

        if (updated.getExamDate() != null)
            existing.setExamDate(updated.getExamDate());

        if (updated.getDurationMinutes() != null)
            existing.setDurationMinutes(updated.getDurationMinutes());

        if (updated.getStudents() != null && !updated.getStudents().isEmpty())
            existing.setStudents(updated.getStudents());

        return examSessionRepository.save(existing);
    }

    // DELETE -------------------------------------------------------
    @Override
    public void deleteSession(Long sessionId) {
        if (!examSessionRepository.existsById(sessionId))
            throw new ApiException("session not found");

        examSessionRepository.deleteById(sessionId);
    }

    @Override
    public void deleteByDate(LocalDate date) {
        List<ExamSession> sessions = examSessionRepository.findByExamDate(date);
        examSessionRepository.deleteAll(sessions);
    }
}
