package com.example.demo.controller;

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
        // Return 201 Created
        return new ResponseEntity<>(service.generatePlan(sessionId), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SeatingPlan> get(@PathVariable Long id) {
        // Do NOT use try-catch here. 
        // Let service.getPlan(id) throw the ApiException, 
        // and your RestExceptionHandler will return the 404.
        return ResponseEntity.ok(service.getPlan(id));
    }

    @GetMapping("/session/{sessionId}")
    public ResponseEntity<List<SeatingPlan>> getBySession(@PathVariable Long sessionId) {
        return ResponseEntity.ok(service.getPlansBySession(sessionId));
    }

    // REQUIRED by test35
    @GetMapping("/list/{sessionId}")
    public ResponseEntity<List<SeatingPlan>> list(@PathVariable Long sessionId) {
        return ResponseEntity.ok(service.getPlansBySession(sessionId));
    }
}