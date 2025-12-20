package com.example.demo.service.impl;

import com.example.demo.exception.ApiException;
import com.example.demo.model.ExamRoom;
import com.example.demo.repository.ExamRoomRepository;
import com.example.demo.service.ExamRoomService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamRoomServiceImpl implements ExamRoomService {

    private final ExamRoomRepository examRoomRepository;

    public ExamRoomServiceImpl(ExamRoomRepository examRoomRepository) {
        this.examRoomRepository = examRoomRepository;
    }

    // CREATE --------------------------------------------------
    @Override
    public ExamRoom addRoom(ExamRoom room) {

        if (room.getRows() <= 0 || room.getColumns() <= 0) {
            throw new ApiException("invalid room size");
        }

        if (examRoomRepository.findByRoomNumber(room.getRoomNumber()).isPresent()) {
            throw new ApiException("room already exists");
        }

        return examRoomRepository.save(room);
    }

    // READ ----------------------------------------------------
    @Override
    public ExamRoom getRoom(Long id) {
        return examRoomRepository.findById(id)
                .orElseThrow(() -> new ApiException("room not found"));
    }

    @Override
    public List<ExamRoom> getAllRooms() {
        return examRoomRepository.findAll();
    }

    // UPDATE ---------------------------------------------------
    @Override
    public ExamRoom updateRoom(Long id, ExamRoom updated) {

        ExamRoom existing = getRoom(id);

        if (updated.getRoomNumber() != null)
            existing.setRoomNumber(updated.getRoomNumber());

        if (updated.getRows() != null)
            existing.setRows(updated.getRows());

        if (updated.getColumns() != null)
            existing.setColumns(updated.getColumns());

        return examRoomRepository.save(existing);
    }

    // DELETE ---------------------------------------------------
    @Override
    public void deleteRoom(Long id) {
        if (!examRoomRepository.existsById(id)) {
            throw new ApiException("room not found");
        }
        examRoomRepository.deleteById(id);
    }

    @Override
    public void deleteByRoomNumber(String roomNumber) {
        ExamRoom room = examRoomRepository.findByRoomNumber(roomNumber)
                .orElseThrow(() -> new ApiException("room not found"));

        examRoomRepository.delete(room);
    }

    @Override
    public void deleteAllRooms() {
        examRoomRepository.deleteAll();
    }
}
