package com.example.demo.service;

import com.example.demo.model.ExamRoom;

import java.util.List;

public interface ExamRoomService {

    // CREATE
    ExamRoom addRoom(ExamRoom room);

    // READ single room by ID
    ExamRoom getRoom(Long id);

    // READ all rooms
    List<ExamRoom> getAllRooms();

    // UPDATE room details
    ExamRoom updateRoom(Long id, ExamRoom updated);

    // DELETE single room by ID
    void deleteRoom(Long id);

    // DELETE room by room number
    void deleteByRoomNumber(String roomNumber);

    // DELETE all rooms
    void deleteAllRooms();
}
