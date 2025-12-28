package com.example.demo.controller;

import com.example.demo.model.ExamSession;
import com.example.demo.service.ExamSessionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sessions")
public class ExamSessionController {

    private final ExamSessionService service;

    public ExamSessionController(ExamSessionService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ExamSession> create(@RequestBody ExamSession s) {
        // RestExceptionHandler will catch the Service's ApiException 
        // and return 400 if students are missing.
        return new ResponseEntity<>(service.createSession(s), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExamSession> get(@PathVariable Long id) {
        return ResponseEntity.ok(service.getSession(id));
    }
}