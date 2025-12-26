package com.example.demo.model;

import com.example.demo.exception.ApiException;
import jakarta.persistence.*;

@Entity
@Table(name = "exam_room")
public class ExamRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String roomNumber;

    @Column(nullable = false)
    private Integer rows;

    @Column(nullable = false)
    private Integer columns;

    @Column(nullable = false)
    private Integer capacity;

    public ExamRoom() {}

    @PrePersist
    public void calculateCapacity() {
        ensureCapacityMatches();
    }

    public void ensureCapacityMatches() {
        if (rows == null || rows <= 0 || columns == null || columns <= 0) {
            throw new ApiException("Invalid room dimensions");
        }
        this.capacity = rows * columns;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getRoomNumber() { return roomNumber; }
    public void setRoomNumber(String roomNumber) { this.roomNumber = roomNumber; }

    public Integer getRows() { return rows; }
    public void setRows(Integer rows) { this.rows = rows; }

    public Integer getColumns() { return columns; }
    public void setColumns(Integer columns) { this.columns = columns; }

    public Integer getCapacity() { return capacity; }
    public void setCapacity(Integer capacity) { this.capacity = capacity; }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private final ExamRoom r = new ExamRoom();

        public Builder id(Long v) { r.id = v; return this; }
        public Builder roomNumber(String v) { r.roomNumber = v; return this; }
        public Builder rows(Integer v) { r.rows = v; return this; }
        public Builder columns(Integer v) { r.columns = v; return this; }
        public Builder capacity(Integer v) { r.capacity = v; return this; }

        public ExamRoom build() { return r; }
    }
}
