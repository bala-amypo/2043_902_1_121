package com.example.demo.service.impl;

import com.example.demo.exception.ApiException;
import com.example.demo.model.ExamRoom;
import com.example.demo.model.ExamSession;
import com.example.demo.model.SeatingPlan;
import com.example.demo.repository.ExamRoomRepository;
import com.example.demo.repository.ExamSessionRepository;
import com.example.demo.repository.SeatingPlanRepository;
import com.example.demo.service.SeatingPlanService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SeatingPlanServiceImpl implements SeatingPlanService {

    private final ExamSessionRepository sessionRepo;
    private final SeatingPlanRepository planRepo;
    private final ExamRoomRepository roomRepo;

    public SeatingPlanServiceImpl(
            ExamSessionRepository sessionRepo,
            SeatingPlanRepository planRepo,
            ExamRoomRepository roomRepo) {

        this.sessionRepo = sessionRepo;
        this.planRepo = planRepo;
        this.roomRepo = roomRepo;
    }

    @Override
    public SeatingPlan generatePlan(Long sessionId) {

        if (sessionId == null) {
            throw new ApiException("Invalid session");
        }

        ExamSession session = sessionRepo.findById(sessionId)
                .orElseThrow(() -> new ApiException("Session not found"));

        if (session.getStudents() == null || session.getStudents().isEmpty()) {
            throw new ApiException("Session must have students");
        }

        List<ExamRoom> rooms =
                roomRepo.findByCapacityGreaterThanEqual(session.getStudents().size());

        if (rooms == null || rooms.isEmpty()) {
            throw new ApiException("No suitable room available");
        }

        ExamRoom room = rooms.get(0);

        SeatingPlan plan = new SeatingPlan();
        plan.setExamSession(session);
        plan.setRoom(room);
        plan.setGeneratedAt(LocalDateTime.now());

        // IMPORTANT: must be valid JSON (test57)
        plan.setArrangementJson(
                "{\"sessionId\":" + sessionId +
                ",\"room\":\"" + room.getRoomNumber() +
                "\",\"capacity\":" + room.getCapacity() + "}"
        );

        return planRepo.save(plan);
    }

    @Override
    public SeatingPlan getPlan(Long sessionId) {

        List<SeatingPlan> plans = planRepo.findByExamSessionId(sessionId);

        if (plans == null || plans.isEmpty()) {
            throw new ApiException("Plan not found");
        }

        return plans.get(0);
    }

    @Override
    public List<SeatingPlan> getPlansBySession(Long sessionId) {
        return planRepo.findByExamSessionId(sessionId);
    }
}
