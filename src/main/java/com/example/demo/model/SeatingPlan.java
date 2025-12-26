package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "seating_plan")
public class SeatingPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "exam_session_id")
    private ExamSession examSession;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private ExamRoom room;

    @Lob
    @Column(name = "arrangement_json")
    private String arrangementJson;

    private LocalDateTime generatedAt;

    public SeatingPlan() {}

    public Long getId(){ return id; }
    public void setId(Long id){ this.id = id; }

    public ExamSession getExamSession(){ return examSession; }
    public void setExamSession(ExamSession examSession){ this.examSession = examSession; }

    public ExamRoom getRoom(){ return room; }
    public void setRoom(ExamRoom room){ this.room = room; }

    public String getArrangementJson(){ return arrangementJson; }
    public void setArrangementJson(String arrangementJson){ this.arrangementJson = arrangementJson; }

    public LocalDateTime getGeneratedAt(){ return generatedAt; }
    public void setGeneratedAt(LocalDateTime generatedAt){ this.generatedAt = generatedAt; }

    public boolean isEmpty() {
        return arrangementJson == null || arrangementJson.trim().isEmpty();
    }
}
