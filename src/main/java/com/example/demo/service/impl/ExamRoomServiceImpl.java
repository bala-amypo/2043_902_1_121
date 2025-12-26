package com.example.demo.service.impl;

import com.example.demo.exception.ApiException;
import com.example.demo.model.ExamRoom;
import com.example.demo.repository.ExamRoomRepository;
import com.example.demo.service.RoomService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {

    private final ExamRoomRepository repo;

    public RoomServiceImpl(ExamRoomRepository repo) {
        this.repo = repo;
    }

    @Override
    public ExamRoom addRoom(ExamRoom room) {

        if (room == null) {
            throw new ApiException("Invalid room");
        }

        if (repo.findByRoomNumber(room.getRoomNumber()).isPresent()) {
            throw new ApiException("Room already exists");
        }

        room.ensureCapacityMatches();
        return repo.save(room);
    }

    @Override
    public List<ExamRoom> getAllRooms() {
        return repo.findAll();
    }
}
