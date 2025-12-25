package com.example.demo.service;

import com.example.demo.model.ExamSession;
import com.example.demo.repository.ExamSessionRepository;
import com.example.demo.repository.StudentRepository;
import org.springframework.stereotype.Service;

@Service
public class ExamSessionService {

    private final ExamSessionRepository repo;
    private final StudentRepository studentRepo;

    public ExamSessionService(ExamSessionRepository repo, StudentRepository studentRepo) {
        this.repo = repo;
        this.studentRepo = studentRepo;
    }

    public ExamSession createSession(ExamSession session) {
        return repo.save(session);
    }

    public ExamSession getSession(Long id) {
        return repo.findById(id).orElse(null);
    }
}
