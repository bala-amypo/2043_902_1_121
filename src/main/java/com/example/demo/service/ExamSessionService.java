// public class ExamSessionService{

// }
package com.example.demo.service;

import com.example.demo.exception.ApiException;
import com.example.demo.model.ExamSession;
import com.example.demo.repository.ExamSessionRepository;
import com.example.demo.repository.StudentRepository;

import java.time.LocalDate;

import org.springframework.stereotype.Service;

@Service
public class ExamSessionService {

    private final ExamSessionRepository examSessionRepository;
    private final StudentRepository studentRepository;

    public ExamSessionService(ExamSessionRepository examSessionRepository,
                              StudentRepository studentRepository) {
        this.examSessionRepository = examSessionRepository;
        this.studentRepository = studentRepository;
    }

    public ExamSession createSession(ExamSession session) {

        if (session.getExamDate().isBefore(LocalDate.now())) {
            throw new ApiException("past date");
        }

        if (session.getStudents() == null || session.getStudents().isEmpty()) {
            throw new ApiException("at least 1 student");
        }

        return examSessionRepository.save(session);
    }

    public ExamSession getSession(Long sessionId) {
        return examSessionRepository.findById(sessionId)
                .orElseThrow(() -> new ApiException("session not found"));
    }
}
