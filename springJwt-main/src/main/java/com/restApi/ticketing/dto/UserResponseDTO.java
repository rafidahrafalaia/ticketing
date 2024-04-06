package com.restApi.ticketing.dto;

import com.restApi.ticketing.model.User;

public class UserResponseDTO {

    private String email;
    private String username;

    public UserResponseDTO(User user) {
        this.email = user.getEmail();
        this.username = user.getUsername();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}