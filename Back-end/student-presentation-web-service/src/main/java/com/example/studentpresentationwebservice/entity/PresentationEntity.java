package com.example.studentpresentationwebservice.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
//@Table(name = "presentation")
public class PresentationEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long presId;

    public long getPresId() {
        return presId;
    }

    public void setPresId(Integer presId) {
        this.presId = presId;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public Integer getTutorId() {
        return tutorId;
    }

    public void setTutorId(Integer tutorId) {
        this.tutorId = tutorId;
    }

    public Date getFinalDate() {
        return finalDate;
    }

    public void setFinalDate(Date finalDate) {
        this.finalDate = finalDate;
    }

    private Integer studentId;

    @Basic
    @Column(length=30, nullable=false)
    private String mode;

    private Integer teacherId;

    private Integer tutorId;

    @Temporal(TemporalType.DATE)
    private Date finalDate;

}
