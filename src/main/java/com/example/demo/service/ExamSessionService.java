package com.example.demo.service;

import com.example.demo.model.ExamSession;

import java.time.LocalDate;
import java.util.List;

public interface ExamSessionService {

    ExamSession createSession(ExamSession session);

    ExamSession getSession(Long id);

    // ✅ tests expect list
    List<ExamSession> getSessionsByDate(LocalDate date);

    List<ExamSession> getAllSessions();
}
