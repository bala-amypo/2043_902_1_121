package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "exam_room")
public class ExamRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String roomNumber;

    // ❗ MariaDB-safe column names
    @Column(name = "row_count", nullable = false)
    private Integer rows;

    @Column(name = "column_count", nullable = false)
    private Integer columns;

    @Column(nullable = false)
    private Integer capacity;

    public ExamRoom() {}

    // ✅ AUTO-CALCULATE CAPACITY (DB + SERVICE SAFE)
    @PrePersist
    @PreUpdate
    public void calculateCapacity() {
        ensureCapacityMatches();
    }

    // ✅ REQUIRED BY TESTS & SERVICE
    public void ensureCapacityMatches() {
        if (rows == null || columns == null) {
            this.capacity = 0;
            return;
        }

        if (rows <= 0 || columns <= 0) {
            this.capacity = 0;
            return;
        }

        this.capacity = rows * columns;
    }

    // ---------------- getters & setters ----------------

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    public Integer getColumns() {
        return columns;
    }

    public void setColumns(Integer columns) {
        this.columns = columns;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    // ---------------- Builder (tests expect this) ----------------

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private final ExamRoom r = new ExamRoom();

        public Builder id(Long id) {
            r.id = id;
            return this;
        }

        public Builder roomNumber(String roomNumber) {
            r.roomNumber = roomNumber;
            return this;
        }

        public Builder rows(Integer rows) {
            r.rows = rows;
            return this;
        }

        public Builder columns(Integer columns) {
            r.columns = columns;
            return this;
        }

        public Builder capacity(Integer capacity) {
            r.capacity = capacity;
            return this;
        }

        public ExamRoom build() {
            return r;
        }
    }
}
