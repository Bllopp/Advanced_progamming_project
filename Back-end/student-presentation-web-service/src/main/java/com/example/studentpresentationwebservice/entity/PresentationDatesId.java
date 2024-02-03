package com.example.studentpresentationwebservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import java.io.Serializable;
import java.util.Date;

@Embeddable
public class PresentationDatesId implements Serializable {
    @Column(name = "presId")
    private Integer presId;

    @Temporal(TemporalType.DATE)
    @Column(name = "date")
    private Date date;

    // constructors, getters, setters, equals(), and hashCode() methods


    public PresentationDatesId(Integer presId, Date date) {
        this.presId = presId;
        this.date = date;
    }

    public PresentationDatesId() {

    }

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
}
