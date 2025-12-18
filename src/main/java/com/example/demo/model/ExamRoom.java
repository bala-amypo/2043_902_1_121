package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "exam_rooms", uniqueConstraints = {
        @UniqueConstraint(columnNames = "roomNumber")
})
public class ExamRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String roomNumber;

    private Integer capacity;

    private Integer rows;

    private Integer columns;

    public ExamRoom() {}

    @PrePersist
    @PreUpdate
    public void ensureCapacityMatches() {
        if (rows != null && columns != null) {
            this.capacity = rows * columns;
        }
    }

    // getters and setters

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getRoomNumber() { return roomNumber; }

    public void setRoomNumber(String roomNumber) { this.roomNumber = roomNumber; }

    public Integer getCapacity() { return capacity; }

    public void setCapacity(Integer capacity) { this.capacity = capacity; }

    public Integer getRows() { return rows; }

    public void setRows(Integer rows) { this.rows = rows; }

    public Integer getColumns() { return columns; }

    public void setColumns(Integer columns) { this.columns = columns; }
}
