package com.example.studentpresentationwebservice.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Vote {

    @JsonProperty("date")
    private String date;

    @JsonProperty("vote")
    private Integer vote;

    @JsonProperty("mode")
    private String mode;

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

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }
}