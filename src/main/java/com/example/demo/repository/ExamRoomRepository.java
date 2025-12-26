package com.example.demo.repository;

import com.example.demo.model.ExamRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExamRoomRepository extends JpaRepository<ExamRoom, Long> {

    // REQUIRED BY TESTS
    Optional<ExamRoom> findByRoomNumber(String roomNumber);

    // REQUIRED BY TESTS
    List<ExamRoom> findByCapacityGreaterThanEqual(int capacity);
}
