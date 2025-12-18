package com.example.demo.controller;

import com.example.demo.model.SeatingPlan;
import com.example.demo.service.SeatingPlanService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/plans")
public class SeatingPlanController {

    private final SeatingPlanService service;

    public SeatingPlanController(SeatingPlanService service) {
        this.service = service;
    }

    @PostMapping("/generate/{sessionId}")
    @Operation(summary = "Generate seating plan")
    public SeatingPlan generate(@PathVariable Long sessionId) {
        return service.generatePlan(sessionId);
    }

    @GetMapping("/{planId}")
    @Operation(summary = "Get seating plan")
    public SeatingPlan get(@PathVariable Long planId) {
        return service.getPlan(planId);
    }

    @GetMapping("/session/{sessionId}")
    @Operation(summary = "Get plans by session")
    public List<SeatingPlan> list(@PathVariable Long sessionId) {
        return service.getPlansBySession(sessionId);
    }
}
