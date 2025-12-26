package com.example.demo.service;

import com.example.demo.model.ExamRoom;
import java.util.List;

public interface ExamRoomService {

    ExamRoom addRoom(ExamRoom room);

    List<ExamRoom> getAllRooms();

    //  THIS METHOD MUST EXIST (cause of @Override error)
    List<ExamRoom> findRoomsByCapacity(int capacity);
}
