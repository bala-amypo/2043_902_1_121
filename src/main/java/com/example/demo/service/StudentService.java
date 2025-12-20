package com.example.demo.service;

import com.example.demo.model.SeatingPlan;

import java.util.List;

public interface SeatingPlanService {

    // CREATE — generate a seating plan
    SeatingPlan generatePlan(Long sessionId);

    // READ — get plan by ID
    SeatingPlan getPlan(Long planId);

    // READ — list plans of a session
    List<SeatingPlan> getPlansBySession(Long sessionId);

    // READ — get all seating plans
    List<SeatingPlan> getAllPlans();

    // UPDATE — edit seating plan manually
    SeatingPlan updatePlan(Long planId, SeatingPlan updated);

    // DELETE — delete plan by ID
    void deletePlan(Long planId);

    // DELETE — delete all plans of a session
    void deletePlansBySession(Long sessionId);
}
