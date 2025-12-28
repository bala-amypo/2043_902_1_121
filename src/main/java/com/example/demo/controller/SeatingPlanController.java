package com.example.demo.controller;

import com.example.demo.exception.ApiException;
import com.example.demo.model.SeatingPlan;
import com.example.demo.service.SeatingPlanService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/seating-plans")
public class SeatingPlanController {

    private final SeatingPlanService service;

    public SeatingPlanController(SeatingPlanService service) {
        this.service = service;
    }

    @PostMapping("/{sessionId}")
    public ResponseEntity<?> generate(@PathVariable Long sessionId) {
        try {
            return ResponseEntity.ok(service.generatePlan(sessionId));
        } catch (ApiException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @GetMapping("/{sessionId}")
    public ResponseEntity<?> get(@PathVariable Long sessionId) {
        try {
            return ResponseEntity.ok(service.getPlan(sessionId));
        } catch (ApiException ex) {
            return ResponseEntity.status(404).body(ex.getMessage());
        }
    }

    @GetMapping("/session/{sessionId}")
    public ResponseEntity<List<SeatingPlan>> getBySession(@PathVariable Long sessionId) {
        return ResponseEntity.ok(service.getPlansBySession(sessionId));
    }

    // REQUIRED BY TEST SUITE
    public ResponseEntity<List<SeatingPlan>> list(Long sessionId) {
        return ResponseEntity.ok(service.getPlansBySession(sessionId));
    }
}
