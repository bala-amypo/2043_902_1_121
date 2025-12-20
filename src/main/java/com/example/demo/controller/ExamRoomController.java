package com.example.demo.controller;

import com.example.demo.model.ExamRoom;
import com.example.demo.service.ExamRoomService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/rooms")
public class ExamRoomController {

    private final ExamRoomService service;

    public ExamRoomController(ExamRoomService service) {
        this.service = service;
    }

    // CREATE --------------------------------------------------------
    @PostMapping
    @Operation(summary = "Add exam room")
    public ExamRoom add(@RequestBody ExamRoom room) {
        return service.addRoom(room);
    }

    // READ ----------------------------------------------------------
    @GetMapping
    @Operation(summary = "List all rooms")
    public List<ExamRoom> list() {
        return service.getAllRooms();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get room by ID")
    public ExamRoom get(@PathVariable Long id) {
        return service.getRoom(id);
    }

    // UPDATE --------------------------------------------------------
    @PutMapping("/{id}")
    @Operation(summary = "Update exam room")
    public ExamRoom update(@PathVariable Long id,
                           @RequestBody ExamRoom room) {
        return service.updateRoom(id, room);
    }

    // DELETE --------------------------------------------------------
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete room by ID")
    public String delete(@PathVariable Long id) {
        service.deleteRoom(id);
        return "deleted";
    }

    @DeleteMapping("/number/{roomNumber}")
    @Operation(summary = "Delete room by room number")
    public String deleteByRoomNumber(@PathVariable String roomNumber) {
        service.deleteByRoomNumber(roomNumber);
        return "deleted room " + roomNumber;
    }

    @DeleteMapping("/all")
    @Operation(summary = "Delete all rooms")
    public String deleteAll() {
        service.deleteAllRooms();
        return "all rooms deleted";
    }
}
