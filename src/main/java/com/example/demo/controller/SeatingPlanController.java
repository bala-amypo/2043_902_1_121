package com.example.demo.controller;

import com.example.demo.exception.ApiException;
import com.example.demo.model.SeatingPlan;
import com.example.demo.service.SeatingPlanService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/seating-plans")
public class SeatingPlanController {

    private final SeatingPlanService service;

    public SeatingPlanController(SeatingPlanService service) {
        this.service = service;
    }

    @PostMapping("/{sessionId}")
    public ResponseEntity<SeatingPlan> generate(@PathVariable Long sessionId) {
        return new ResponseEntity<>(service.generatePlan(sessionId), HttpStatus.CREATED);
    }

    @GetMapping("/{sessionId}")
    public ResponseEntity<SeatingPlan> get(@PathVariable Long sessionId) {
        try {
            SeatingPlan plan = service.getPlan(sessionId);
            if (plan == null) return ResponseEntity.notFound().build();
            return ResponseEntity.ok(plan);
        } catch (ApiException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/session/{sessionId}")
    public ResponseEntity<List<SeatingPlan>> getBySession(@PathVariable Long sessionId) {
        return ResponseEntity.ok(service.getPlansBySession(sessionId));
    }

    // Required by the automated test suite
    @GetMapping("/list/{sessionId}")
    public ResponseEntity<List<SeatingPlan>> list(@PathVariable Long sessionId) {
        return ResponseEntity.ok(service.getPlansBySession(sessionId));
    }
}