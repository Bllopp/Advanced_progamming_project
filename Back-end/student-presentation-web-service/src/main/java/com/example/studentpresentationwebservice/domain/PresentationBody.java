package com.example.studentpresentationwebservice.domain;


import com.fasterxml.jackson.annotation.JsonProperty;

public class PresentationBody {

    @JsonProperty("studentId")
    private Integer studentId;

    @JsonProperty("mode1")
    private String mode1;

    @JsonProperty("mode2")
    private String mode2;

    @JsonProperty("mode3")
    private String mode3;

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

    public String getMode1() {
        return mode1;
    }

    public void setMode1(String mode1) {
        this.mode1 = mode1;
    }

    public String getMode2() {
        return mode2;
    }

    public void setMode2(String mode2) {
        this.mode2 = mode2;
    }

    public String getMode3() {
        return mode3;
    }

    public void setMode3(String mode3) {
        this.mode3 = mode3;
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
