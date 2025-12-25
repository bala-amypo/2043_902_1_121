package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.model.SeatingPlan;

public interface SeatingPlanRepository extends JpaRepository<SeatingPlan, Long> {
    SeatingPlan findByExamSessionId(Long sessionId);
}
