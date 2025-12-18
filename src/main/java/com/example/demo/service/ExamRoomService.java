// public class ExamRoomService{

// }
package com.example.demo.service;

import com.example.demo.exception.ApiException;
import com.example.demo.model.ExamRoom;
import com.example.demo.repository.ExamRoomRepository;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class ExamRoomService {

    private final ExamRoomRepository examRoomRepository;

    public ExamRoomService(ExamRoomRepository examRoomRepository) {
        this.examRoomRepository = examRoomRepository;
    }

    public ExamRoom addRoom(ExamRoom room) {

        if (examRoomRepository.findByRoomNumber(room.getRoomNumber()) != null) {
            throw new ApiException("exists");
        }

        return examRoomRepository.save(room);
    }

    public List<ExamRoom> getAllRooms() {
        return examRoomRepository.findAll();
    }
}
