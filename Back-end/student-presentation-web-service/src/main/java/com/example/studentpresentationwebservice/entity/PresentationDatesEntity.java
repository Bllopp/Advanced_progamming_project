package com.example.studentpresentationwebservice.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "presentationdates")
public class PresentationDatesEntity implements Serializable {
    @EmbeddedId
    private PresentationDatesId dateId;

    @Basic
    @Column(length=30, nullable=false, name = "mode")
    private String mode;

    @Column(name = "teacherVote")
    private Integer teacherVote;

    @Column(name = "tutorVote")
    private Integer tutorVote;

    public PresentationDatesId getDateId() {
        return dateId;
    }

    public void setDateId(PresentationDatesId dateId) {
        this.dateId = dateId;
    }

    public Integer getTeacherVote() {
        return teacherVote;
    }

    public void setTeacherVote(Integer teacherVote) {
        this.teacherVote = teacherVote;
    }

    public Integer getTutorVote() {
        return tutorVote;
    }

    public void setTutorVote(Integer tutotVote) {
        this.tutorVote = tutotVote;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }
}
