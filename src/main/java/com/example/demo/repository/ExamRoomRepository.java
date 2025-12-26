package com.example.demo.repository;

import com.example.demo.model.ExamRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ExamRoomRepository extends JpaRepository<ExamRoom, Long> {

    //  THIS METHOD MUST EXIST (cause of cannot find symbol error)
    @Query("SELECT r FROM ExamRoom r WHERE r.capacity >= :capacity")
    List<ExamRoom> findRoomsByCapacity(int capacity);

    boolean existsByRoomNumber(String roomNumber);
}
