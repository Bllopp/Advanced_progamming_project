package com.example.studentpresentationwebservice.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "presentationdates")
@IdClass(PresentationDatesEntity.class)
public class PresentationDatesEntity implements Serializable {
    @Id
    @Column(name = "presId")
    private Integer presId;

    @Id
    @Temporal(TemporalType.DATE)
    @Column(name = "date")
    private Date date;

    @Column(name = "teacherVote")
    private Integer teacherVote;

    @Column(name = "tutorVote")
    private Integer tutorVote;

    public Integer getPresId() {
        return presId;
    }

    public void setPresId(Integer presId) {
        this.presId = presId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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
}
