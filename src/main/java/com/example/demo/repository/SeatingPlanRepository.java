package com.example.demo.repository;

import com.example.demo.model.SeatingPlan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SeatingPlanRepository extends JpaRepository<SeatingPlan, Long> {

    // ✅ tests expect LIST
    List<SeatingPlan> findByExamSessionId(Long sessionId);
}
