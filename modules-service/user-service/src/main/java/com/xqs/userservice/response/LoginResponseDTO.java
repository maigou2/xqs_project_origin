package com.xqs.userservice.response;

import com.xqs.userapi.info.UserInfo;
import lombok.Data;

@Data
public class LoginResponseDTO{

    private boolean status;

    private String message;

    private String token;

    private UserInfo userInfo;

    public LoginResponseDTO(boolean status, String message, String token, UserInfo userInfo) {
        this.status = status;
        this.message = message;
        this.token = token;
        this.userInfo = userInfo;
    }

    public LoginResponseDTO() {
    }

    public LoginResponseDTO(boolean status, String message) {
        this.status = status;
        this.message = message;
    }

    public LoginResponseDTO(boolean status) {
        this.status = status;
        this.message = "操作成功";
    }
}
