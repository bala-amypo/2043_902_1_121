package com.example.demo.service;

import com.example.demo.model.ExamSession;

import java.time.LocalDate;
import java.util.List;

public interface ExamSessionService {

    // CREATE session
    ExamSession createSession(ExamSession session);

    // READ session by ID
    ExamSession getSession(Long sessionId);

    // READ all sessions
    List<ExamSession> getAllSessions();

    // UPDATE session details
    ExamSession updateSession(Long sessionId, ExamSession updated);

    // DELETE session by ID
    void deleteSession(Long sessionId);

    // DELETE all sessions on a given date
    void deleteByDate(LocalDate date);
}
