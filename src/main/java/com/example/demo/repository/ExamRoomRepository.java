package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.model.ExamRoom;

import java.util.List;
import java.util.Optional;

public interface ExamRoomRepository extends JpaRepository<ExamRoom, Long> {

    // ✅ Tests expect Optional
    Optional<ExamRoom> findByRoomNumber(String roomNumber);

    List<ExamRoom> findByCapacityGreaterThanEqual(Integer capacity);
}
