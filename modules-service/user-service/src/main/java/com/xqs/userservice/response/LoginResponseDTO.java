package com.xqs.userservice.response;

import lombok.Data;

@Data
public class LoginResponseDTO{

    private boolean status;

    private String message;

    public LoginResponseDTO() {
    }

    public LoginResponseDTO(boolean status, String message) {
        this.status = status;
        this.message = message;
    }

    public LoginResponseDTO(boolean status) {
        this.status = status;
    }
}
