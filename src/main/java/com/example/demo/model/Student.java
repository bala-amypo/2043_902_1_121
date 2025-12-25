package com.example.demo.model;

import jakarta.persistence.*;

@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String rollNumber;

    private String name;
    private String department;
    private Integer year;

    public Student() {}

    // getters & setters
    public Long getId(){ return id; }
    public void setId(Long id){ this.id = id; }

    public String getRollNumber(){ return rollNumber; }
    public void setRollNumber(String rollNumber){ this.rollNumber = rollNumber; }

    public String getName(){ return name; }
    public void setName(String name){ this.name = name; }

    public String getDepartment(){ return department; }
    public void setDepartment(String department){ this.department = department; }

    public Integer getYear(){ return year; }
    public void setYear(Integer year){ this.year = year; }

    // ✅ Builder (tests expect id())
    public static Builder builder(){ return new Builder(); }

    public static class Builder {
        private final Student s = new Student();

        public Builder id(Long v){ s.id = v; return this; }
        public Builder rollNumber(String v){ s.rollNumber = v; return this; }
        public Builder name(String v){ s.name = v; return this; }
        public Builder department(String v){ s.department = v; return this; }
        public Builder year(Integer v){ s.year = v; return this; }

        public Student build(){ return s; }
    }
}
