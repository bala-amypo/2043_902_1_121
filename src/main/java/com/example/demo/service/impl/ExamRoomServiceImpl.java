package com.example.demo.service.impl;

import com.example.demo.exception.ApiException;
import com.example.demo.model.ExamRoom;
import com.example.demo.repository.ExamRoomRepository;
import com.example.demo.service.ExamRoomService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamRoomServiceImpl implements ExamRoomService {

    private final ExamRoomRepository repo;

    public ExamRoomServiceImpl(ExamRoomRepository repo) {
        this.repo = repo;
    }

    @Override
    public ExamRoom addRoom(ExamRoom room) {

        // 1. Null check
        if (room == null) {
            throw new ApiException("Invalid room data");
        }

        // 2. Mandatory fields check
        if (room.getRoomNumber() == null ||
            room.getRoomNumber().trim().isEmpty() ||
            room.getRows() == null ||
            room.getColumns() == null) {

            throw new ApiException("Invalid room data");
        }

        // 3. Negative / zero validation (IMPORTANT FOR test37)
        if (room.getRows() <= 0 || room.getColumns() <= 0) {
            throw new ApiException("Invalid room size");
        }

        // 4. Duplicate room number check (IMPORTANT FOR test26)
        if (repo.findByRoomNumber(room.getRoomNumber()).isPresent()) {
            throw new ApiException("Room with number already exists");
        }

        // 5. Capacity calculation (IMPORTANT FOR test52)
        room.ensureCapacityMatches();

        return repo.save(room);
    }

    @Override
    public List<ExamRoom> getAllRooms() {
        return repo.findAll();
    }

    @Override
    public List<ExamRoom> findRoomsByCapacity(int capacity) {
        return repo.findByCapacityGreaterThanEqual(capacity);
    }
}
