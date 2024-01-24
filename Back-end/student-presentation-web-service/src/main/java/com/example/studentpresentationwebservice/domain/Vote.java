package com.example.studentpresentationwebservice.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Vote {

    @JsonProperty("date")
    private String date;

    @JsonProperty("vote")
    private Integer vote;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getVote() {
        return vote;
    }

    public void setVote(Integer vote) {
        this.vote = vote;
    }
}