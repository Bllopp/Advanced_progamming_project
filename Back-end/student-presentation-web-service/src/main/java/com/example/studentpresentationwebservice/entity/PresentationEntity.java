package com.example.studentpresentationwebservice.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "presentation")
public class PresentationEntity {
    @Id
    @Column(name = "presId")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int presId;

    @Column(name = "studentId")
    private int studentId;

    @Basic
    @Column(length=30, nullable=false, name = "mode")
    private String mode;

    @Column(name = "teacherId")
    private int teacherId;

    @Column(name = "tutorId")
    private int tutorId;

    @Temporal(TemporalType.DATE)
    @Column(name = "finalDate")
    private Date finalDate;

    public int getPresId() {
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

    @Override
    public String toString() {
        return "PresentationEntity{" +
                "presId=" + presId +
                ", studentId=" + studentId +
                ", mode='" + mode + '\'' +
                ", teacherId=" + teacherId +
                ", tutorId=" + tutorId +
                ", finalDate=" + finalDate +
                '}';
    }
}
