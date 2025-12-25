package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class SeatingPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private ExamSession examSession;

    @ManyToOne
    private ExamRoom room;

    @Column(columnDefinition = "TEXT")
    private String arrangementJson;

    private LocalDateTime generatedAt;

    @PrePersist
    public void onCreate(){
        generatedAt = LocalDateTime.now();
    }

    public Long getId(){return id;}
    public ExamSession getExamSession(){return examSession;}
    public ExamRoom getRoom(){return room;}
    public String getArrangementJson(){return arrangementJson;}
}
