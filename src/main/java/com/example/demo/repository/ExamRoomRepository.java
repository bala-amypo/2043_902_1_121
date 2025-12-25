package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.model.ExamRoom;
import java.util.List;

public interface ExamRoomRepository extends JpaRepository<ExamRoom, Long> {
    ExamRoom findByRoomNumber(String roomNumber);
    List<ExamRoom> findByCapacityGreaterThanEqual(Integer capacity);
}
