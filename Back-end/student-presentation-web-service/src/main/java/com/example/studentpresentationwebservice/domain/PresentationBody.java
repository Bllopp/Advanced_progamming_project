package com.example.studentpresentationwebservice.domain;


import com.fasterxml.jackson.annotation.JsonProperty;

public class PresentationBody {

    @JsonProperty("studentId")
    private Integer studentId;

    @JsonProperty("mode")
    private String mode;

    @JsonProperty("teacherId")
    private Integer teacherId;

    @JsonProperty("tutorId")
    private Integer tutorId;

    @JsonProperty("date1")
    private String date1;

    @JsonProperty("date2")
    private String date2;

    @JsonProperty("date3")
    private String date3;

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

    public String getDate1() {
        return date1;
    }

    public void setDate1(String date1) {
        this.date1 = date1;
    }

    public String getDate2() {
        return date2;
    }

    public void setDate2(String date2) {
        this.date2 = date2;
    }

    public String getDate3() {
        return date3;
    }

    public void setDate3(String date3) {
        this.date3 = date3;
    }
}
