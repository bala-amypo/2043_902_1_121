package com.example.demo.controller;

import com.example.demo.model.ExamRoom;
import com.example.demo.service.ExamRoomService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rooms")
public class ExamRoomController {

    private final ExamRoomService service;

    public ExamRoomController(ExamRoomService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ExamRoom> add(@RequestBody ExamRoom r) {
        return ResponseEntity.ok(service.addRoom(r));
    }

    // ✅ Existing endpoint (keep)
    @GetMapping
    public ResponseEntity<List<ExamRoom>> getAll() {
        return ResponseEntity.ok(service.getAllRooms());
    }

    // ✅ REQUIRED by test suite
    public ResponseEntity<List<ExamRoom>> list() {
        return ResponseEntity.ok(service.getAllRooms());
    }
}
