package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "exam_room") // 🔒 lock table name
public class ExamRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String roomNumber;

    @Column(name = "row_count", nullable = false)
    private Integer rows;

    @Column(name = "column_count", nullable = false)
    private Integer columns;

    @Column(nullable = false)
    private Integer capacity;

    public ExamRoom() {}

    @PrePersist
    public void calculateCapacity() {
        if (rows != null && columns != null) {
            capacity = rows * columns;
        }
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
}
