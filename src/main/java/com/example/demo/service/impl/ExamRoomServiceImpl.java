package com.example.demo.service.impl;

import com.example.demo.exception.ApiException;
import com.example.demo.model.ExamRoom;
import com.example.demo.repository.ExamRoomRepository;
import com.example.demo.service.ExamRoomService;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class ExamRoomServiceImpl implements ExamRoomService {

    private final ExamRoomRepository examRoomRepository;

    public ExamRoomServiceImpl(ExamRoomRepository examRoomRepository) {
        this.examRoomRepository = examRoomRepository;
    }

    @Override
    public ExamRoom addRoom(ExamRoom room) {

        if (examRoomRepository.findByRoomNumber(room.getRoomNumber()) != null) {
            throw new ApiException("exists");
        }

        return examRoomRepository.save(room);
    }

    @Override
    public List<ExamRoom> getAllRooms() {
        return examRoomRepository.findAll();
    }
}
