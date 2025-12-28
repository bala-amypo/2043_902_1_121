package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
public class ExamSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String courseCode;
    private LocalDate examDate;
    private String examTime;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "session_students",
        joinColumns = @JoinColumn(name = "session_id"),
        inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    private Set<Student> students;

    public ExamSession() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCourseCode() { return courseCode; }
    public void setCourseCode(String v) { this.courseCode = v; }

    public LocalDate getExamDate() { return examDate; }
    public void setExamDate(LocalDate v) { this.examDate = v; }

    public String getExamTime() { return examTime; }
    public void setExamTime(String v) { this.examTime = v; }

    public Set<Student> getStudents() { return students; }
    public void setStudents(Set<Student> v) { this.students = v; }

    // Helper for validation logic
    public boolean hasStudents() {
        return students != null && !students.isEmpty();
    }

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private final ExamSession e = new ExamSession();
        public Builder id(Long v) { e.id = v; return this; }
        public Builder courseCode(String v) { e.courseCode = v; return this; }
        public Builder examDate(LocalDate v) { e.examDate = v; return this; }
        public Builder examTime(String v) { e.examTime = v; return this; }
        public Builder students(Set<Student> v) { e.students = v; return this; }
        public ExamSession build() { return e; }
    }
}