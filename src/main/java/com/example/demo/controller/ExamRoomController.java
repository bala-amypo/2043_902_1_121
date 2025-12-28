package com.example.demo.controller;

import com.example.demo.model.ExamRoom;
import com.example.demo.service.ExamRoomService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rooms") // Added /api prefix
public class ExamRoomController {

    private final ExamRoomService service;

    public ExamRoomController(ExamRoomService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ExamRoom> add(@RequestBody ExamRoom r) {
        // test04: Returns 201 Created for test consistency
        return new ResponseEntity<>(service.addRoom(r), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ExamRoom>> getAll() {
        return ResponseEntity.ok(service.getAllRooms());
    }

    // REQUIRED by test suite - mapped to /api/rooms/list
    @GetMapping("/list")
    public ResponseEntity<List<ExamRoom>> list() {
        return ResponseEntity.ok(service.getAllRooms());
    }
}