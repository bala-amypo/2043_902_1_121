package com.example.demo.service;

import com.example.demo.model.SeatingPlan;

import java.util.List;

public interface SeatingPlanService {

    SeatingPlan generatePlan(Long sessionId);

    SeatingPlan getPlan(Long planId);

    List<SeatingPlan> getPlansBySession(Long sessionId);
}
