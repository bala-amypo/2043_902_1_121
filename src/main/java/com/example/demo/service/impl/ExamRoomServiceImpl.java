package com.example.demo.service.impl;

import com.example.demo.exception.ApiException;
import com.example.demo.model.ExamRoom;
import com.example.demo.repository.ExamRoomRepository;
import com.example.demo.service.ExamRoomService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExamRoomServiceImpl implements ExamRoomService {

    private final ExamRoomRepository repo;

    public ExamRoomServiceImpl(ExamRoomRepository repo) {
        this.repo = repo;
    }

    @Override
    public ExamRoom addRoom(ExamRoom room) {

        // 🔴 missing fields
        if (room.getRoomNumber() == null ||
            room.getRows() == null ||
            room.getColumns() == null) {
            throw new ApiException("Invalid room data");
        }

        // 🔴 negative rows / columns
        if (room.getRows() <= 0 || room.getColumns() <= 0) {
            throw new ApiException("Invalid room dimensions");
        }

        // 🔴 duplicate room number
        Optional<ExamRoom> existing =
                repo.findByRoomNumber(room.getRoomNumber());

        if (existing.isPresent()) {
            throw new ApiException("Room number already exists");
        }

        // capacity auto calculated
        room.ensureCapacityMatches();
        return repo.save(room);
    }

    @Override
    public List<ExamRoom> getAllRooms() {
        return repo.findAll();
    }
}
