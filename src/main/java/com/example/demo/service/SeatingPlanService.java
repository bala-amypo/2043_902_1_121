package com.example.demo.service;

import com.example.demo.model.SeatingPlan;
import com.example.demo.repository.ExamRoomRepository;
import com.example.demo.repository.ExamSessionRepository;
import com.example.demo.repository.SeatingPlanRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeatingPlanService {

    private final ExamSessionRepository sessionRepo;
    private final SeatingPlanRepository planRepo;
    private final ExamRoomRepository roomRepo;

    public SeatingPlanService(
            ExamSessionRepository sessionRepo,
            SeatingPlanRepository planRepo,
            ExamRoomRepository roomRepo
    ) {
        this.sessionRepo = sessionRepo;
        this.planRepo = planRepo;
        this.roomRepo = roomRepo;
    }

    public SeatingPlan generatePlan(Long sessionId) {
        SeatingPlan plan = new SeatingPlan();
        return planRepo.save(plan);
    }

    public SeatingPlan getPlan(Long sessionId) {
        return planRepo.findByExamSessionId(sessionId);
    }

    public List<SeatingPlan> getPlansBySession(Long sessionId) {
        SeatingPlan plan = planRepo.findByExamSessionId(sessionId);
        return plan == null ? List.of() : List.of(plan);
    }
}
