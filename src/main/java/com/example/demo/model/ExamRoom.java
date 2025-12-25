package com.example.demo.model;

import jakarta.persistence.*;

@Entity
public class ExamRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String roomNumber;

    private Integer capacity;
    private Integer rows;
    private Integer columns;

    public ExamRoom(){}

    @PrePersist
    public void ensureCapacityMatches(){
        if(rows!=null && columns!=null){
            capacity = rows * columns;
        }
    }

    public Long getId(){return id;}
    public void setId(Long id){this.id=id;}

    public String getRoomNumber(){return roomNumber;}
    public void setRoomNumber(String roomNumber){this.roomNumber=roomNumber;}

    public Integer getCapacity(){return capacity;}
    public void setCapacity(Integer capacity){this.capacity=capacity;}

    public Integer getRows(){return rows;}
    public void setRows(Integer rows){this.rows=rows;}

    public Integer getColumns(){return columns;}
    public void setColumns(Integer columns){this.columns=columns;}

    public static Builder builder(){return new Builder();}
    public static class Builder{
        private final ExamRoom r=new ExamRoom();
        public Builder roomNumber(String v){r.roomNumber=v;return this;}
        public Builder rows(Integer v){r.rows=v;return this;}
        public Builder columns(Integer v){r.columns=v;return this;}
        public Builder capacity(Integer v){r.capacity=v;return this;}
        public ExamRoom build(){return r;}
    }
}
