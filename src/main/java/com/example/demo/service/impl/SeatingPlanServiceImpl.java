package com.example.demo.service.impl;

import com.example.demo.exception.ApiException;
import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.SeatingPlanService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SeatingPlanServiceImpl implements SeatingPlanService {

    private final ExamSessionRepository examSessionRepository;
    private final SeatingPlanRepository seatingPlanRepository;
    private final ExamRoomRepository examRoomRepository;

    public SeatingPlanServiceImpl(ExamSessionRepository examSessionRepository,
                                  SeatingPlanRepository seatingPlanRepository,
                                  ExamRoomRepository examRoomRepository) {
        this.examSessionRepository = examSessionRepository;
        this.seatingPlanRepository = seatingPlanRepository;
        this.examRoomRepository = examRoomRepository;
    }

    // CREATE ---------------------------------------------------------
    @Override
    public SeatingPlan generatePlan(Long sessionId) {

        ExamSession session = examSessionRepository.findById(sessionId)
                .orElseThrow(() -> new ApiException("session not found"));

        int studentCount = session.getStudents().size();
        List<ExamRoom> rooms =
                examRoomRepository.findByCapacityGreaterThanEqual(studentCount);

        if (rooms.isEmpty()) {
            throw new ApiException("no room available");
        }

        ExamRoom room = rooms.get(0);

        Map<String, String> arrangement = new LinkedHashMap<>();
        int seat = 1;
        for (Student s : session.getStudents()) {
            arrangement.put("Seat-" + seat++, s.getRollNumber());
        }

        try {
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(arrangement);

            SeatingPlan plan = new SeatingPlan();
            plan.setExamSession(session);
            plan.setRoom(room);
            plan.setArrangementJson(json);

            return seatingPlanRepository.save(plan);

        } catch (Exception e) {
            throw new ApiException("failed to generate plan");
        }
    }

    // READ ---------------------------------------------------------
    @Override
    public SeatingPlan getPlan(Long planId) {
        return seatingPlanRepository.findById(planId)
                .orElseThrow(() -> new ApiException("plan not found"));
    }

    @Override
    public List<SeatingPlan> getPlansBySession(Long sessionId) {
        return seatingPlanRepository.findByExamSessionId(sessionId);
    }

    @Override
    public List<SeatingPlan> getAllPlans() {
        return seatingPlanRepository.findAll();
    }

    // UPDATE ---------------------------------------------------------
    @Override
    public SeatingPlan updatePlan(Long planId, SeatingPlan updated) {
        SeatingPlan existing = getPlan(planId);

        if (updated.getArrangementJson() != null)
            existing.setArrangementJson(updated.getArrangementJson());

        if (updated.getRoom() != null)
            existing.setRoom(updated.getRoom());

        return seatingPlanRepository.save(existing);
    }

    // DELETE ---------------------------------------------------------
    @Override
    public void deletePlan(Long planId) {
        if (!seatingPlanRepository.existsById(planId)) {
            throw new ApiException("plan not found");
        }
        seatingPlanRepository.deleteById(planId);
    }

    @Override
    public void deletePlansBySession(Long sessionId) {
        seatingPlanRepository.deleteByExamSessionId(sessionId);
    }
}
