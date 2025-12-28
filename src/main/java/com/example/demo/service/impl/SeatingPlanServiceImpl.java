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

        ExamSession session = sessionRepo.findById(sessionId)
                .orElseThrow(() -> new ApiException("Session not found"));

        if (session.getStudents() == null || session.getStudents().isEmpty()) {
            throw new ApiException("Students are required for session");
        }

        List<ExamRoom> rooms = roomRepo.findAll();
        if (rooms == null || rooms.isEmpty()) {
            throw new ApiException("No rooms available");
        }

        int studentCount = session.getStudents().size();

        // 🔑 FIX: pick first room with sufficient capacity
        ExamRoom selectedRoom = rooms.stream()
                .filter(r -> r.getCapacity() >= studentCount)
                .findFirst()
                .orElseThrow(() -> new ApiException("No suitable room found"));

        SeatingPlan plan = new SeatingPlan();
        plan.setGeneratedAt(LocalDateTime.now());
        plan.setExamSession(session);
        plan.setRoom(selectedRoom);

        plan.setArrangementJson(
                "{\"sessionId\":" + sessionId +
                ",\"room\":\"" + selectedRoom.getRoomNumber() +
                "\",\"capacity\":" + selectedRoom.getCapacity() +
                ",\"students\":[]}"
        );

        return planRepo.save(plan);
    }

    @Override
    public SeatingPlan getPlan(Long sessionId) {

        List<SeatingPlan> plans = planRepo.findByExamSessionId(sessionId);

        if (plans == null || plans.isEmpty()) {
            throw new ApiException("Seating plan not found");
        }

        return plans.get(0);
    }

    @Override
    public List<SeatingPlan> getPlansBySession(Long sessionId) {
        List<SeatingPlan> plans = planRepo.findByExamSessionId(sessionId);
        return plans == null ? List.of() : plans;
    }
}
