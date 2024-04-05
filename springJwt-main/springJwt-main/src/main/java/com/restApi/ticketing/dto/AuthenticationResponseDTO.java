package com.restApi.ticketing.dto;

public class AuthenticationResponseDTO {
    private String token;
    private String message;

    public AuthenticationResponseDTO(String token, String message) {
        this.token = token;
        this.message = message;
    }

    public String getToken() {
        return token;
    }

    public String getMessage() {
        return message;
    }
}
