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

    // CREATE
    @PostMapping("/generate/{sessionId}")
    @Operation(summary = "Generate seating plan")
    public SeatingPlan generate(@PathVariable Long sessionId) {
        return service.generatePlan(sessionId);
    }

    // READ (one)
    @GetMapping("/{planId}")
    @Operation(summary = "Get seating plan by ID")
    public SeatingPlan get(@PathVariable Long planId) {
        return service.getPlan(planId);
    }

    // READ (session)
    @GetMapping("/session/{sessionId}")
    @Operation(summary = "Get all seating plans of a session")
    public List<SeatingPlan> list(@PathVariable Long sessionId) {
        return service.getPlansBySession(sessionId);
    }

    // READ (all)
    @GetMapping
    @Operation(summary = "Get all seating plans")
    public List<SeatingPlan> listAll() {
        return service.getAllPlans();
    }

    // UPDATE
    @PutMapping("/{planId}")
    @Operation(summary = "Update seating plan")
    public SeatingPlan update(@PathVariable Long planId,
                              @RequestBody SeatingPlan updated) {
        return service.updatePlan(planId, updated);
    }

    // DELETE (one)
    @DeleteMapping("/{planId}")
    @Operation(summary = "Delete seating plan by ID")
    public String delete(@PathVariable Long planId) {
        service.deletePlan(planId);
        return "deleted";
    }

    // DELETE (session)
    @DeleteMapping("/session/{sessionId}")
    @Operation(summary = "Delete all plans of a session")
    public String deleteBySession(@PathVariable Long sessionId) {
        service.deletePlansBySession(sessionId);
        return "deleted all for session " + sessionId;
    }
}
