package com.example.testauthservice.dto;

import com.example.testauthservice.entity.UserEntity;

public class UserDto {

    private Long id;

    private String mail;

    private String username;

    private String role;

    public UserDto(UserEntity user){

        this.mail = (user.getEmail());
        this.role = (user.getRoles());
        this.id = (user.getId());
        this.username = (user.getUsername());

    }

    public UserDto() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
