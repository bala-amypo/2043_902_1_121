package com.example.demo.controller;

import com.example.demo.model.ExamSession;
import com.example.demo.service.ExamSessionService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/sessions")
public class ExamSessionController {

    private final ExamSessionService service;

    public ExamSessionController(ExamSessionService service) {
        this.service = service;
    }
    @PostMapping
    @Operation(summary = "Create exam session")
    public ExamSession create(@RequestBody ExamSession session) {
        return service.createSession(session);
    }
    @GetMapping("/{id}")
    @Operation(summary = "Get exam session by ID")
    public ExamSession get(@PathVariable Long id) {
        return service.getSession(id);
    }

    @GetMapping
    @Operation(summary = "Get all exam sessions")
    public List<ExamSession> getAll() {
        return service.getAllSessions();
    }

    // UPDATE ---------------------------------------------------------
    @PutMapping("/{id}")
    @Operation(summary = "Update exam session")
    public ExamSession update(@PathVariable Long id,
                              @RequestBody ExamSession updated) {
        return service.updateSession(id, updated);
    }

    // DELETE ---------------------------------------------------------
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete exam session by ID")
    public String delete(@PathVariable Long id) {
        service.deleteSession(id);
        return "deleted";
    }

    @DeleteMapping("/date/{date}")
    @Operation(summary = "Delete all sessions on a specific date")
    public String deleteByDate(@PathVariable String date) {
        service.deleteByDate(LocalDate.parse(date));
        return "deleted all for date " + date;
    }
}
