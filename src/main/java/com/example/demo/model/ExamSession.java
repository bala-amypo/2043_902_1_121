package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class ExamSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String courseCode;
    private LocalDate examDate;
    private String examTime;

    @ManyToMany
    private List<Student> students;

    public ExamSession(){}

    public Long getId(){return id;}
    public void setId(Long id){this.id=id;}

    public String getCourseCode(){return courseCode;}
    public void setCourseCode(String courseCode){this.courseCode=courseCode;}

    public LocalDate getExamDate(){return examDate;}
    public void setExamDate(LocalDate examDate){this.examDate=examDate;}

    public String getExamTime(){return examTime;}
    public void setExamTime(String examTime){this.examTime=examTime;}

    public List<Student> getStudents(){return students;}
    public void setStudents(List<Student> students){this.students=students;}

    public static Builder builder(){return new Builder();}
    public static class Builder{
        private final ExamSession e=new ExamSession();
        public Builder courseCode(String v){e.courseCode=v;return this;}
        public Builder examDate(LocalDate v){e.examDate=v;return this;}
        public Builder examTime(String v){e.examTime=v;return this;}
        public Builder students(List<Student> v){e.students=v;return this;}
        public ExamSession build(){return e;}
    }
}
