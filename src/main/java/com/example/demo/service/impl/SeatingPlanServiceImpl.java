package com.example.demo.service.impl;

import com.example.demo.exception.ApiException;
import com.example.demo.model.ExamRoom;
import com.example.demo.model.ExamSession;
import com.example.demo.model.SeatingPlan;
import com.example.demo.model.Student;
import com.example.demo.repository.ExamRoomRepository;
import com.example.demo.repository.ExamSessionRepository;
import com.example.demo.repository.SeatingPlanRepository;
import com.example.demo.service.SeatingPlanService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SeatingPlanServiceImpl implements SeatingPlanService {

    private final ExamSessionRepository sessionRepo;
    private final SeatingPlanRepository planRepo;
    private final ExamRoomRepository roomRepo;

    public SeatingPlanServiceImpl(ExamSessionRepository sessionRepo,
                                  SeatingPlanRepository planRepo,
                                  ExamRoomRepository roomRepo) {
        this.sessionRepo = sessionRepo;
        this.planRepo = planRepo;
        this.roomRepo = roomRepo;
    }

    @Override
    public SeatingPlan generatePlan(Long sessionId) {
        ExamSession session = sessionRepo.findById(sessionId)
                .orElseThrow(() -> new ApiException("session not found"));

        int studentCount = session.getStudents().size();
        List<ExamRoom> rooms = roomRepo.findByCapacityGreaterThanEqual(studentCount);

        if (rooms.isEmpty()) throw new ApiException("no room available");

        ExamRoom room = rooms.get(0);

        Map<String, String> arrangement = new LinkedHashMap<>();
        int seatNo = 1;

        for (Student s : session.getStudents()) {
            arrangement.put("Seat-" + seatNo++, s.getRollNumber());
        }

        try {
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(arrangement);

            SeatingPlan plan = new SeatingPlan();
            plan.setExamSession(session);
            plan.setRoom(room);
            plan.setArrangementJson(json);

            return planRepo.save(plan);

        } catch (Exception e) {
            throw new ApiException("failed to generate plan");
        }
    }

    @Override
    public SeatingPlan getPlan(Long planId) {
        return planRepo.findById(planId)
                .orElseThrow(() -> new ApiException("plan not found"));
    }

    @Override
    public List<SeatingPlan> getPlansBySession(Long sessionId) {
        return planRepo.findByExamSessionId(sessionId);
    }

    @Override
    public List<SeatingPlan> getAllPlans() {
        return planRepo.findAll();
    }

    @Override
    public SeatingPlan updatePlan(Long planId, SeatingPlan updated) {
        SeatingPlan plan = getPlan(planId);

        plan.setArrangementJson(updated.getArrangementJson());
        plan.setRoom(updated.getRoom());
        plan.setExamSession(updated.getExamSession());

        return planRepo.save(plan);
    }

    @Override
    public void deletePlan(Long planId) {
        if (!planRepo.existsById(planId)) {
            throw new ApiException("plan not found");
        }
        planRepo.deleteById(planId);
    }

    @Override
    public void deletePlansBySession(Long sessionId) {
        List<SeatingPlan> plans = planRepo.findByExamSessionId(sessionId);
        planRepo.deleteAll(plans);
    }
}
