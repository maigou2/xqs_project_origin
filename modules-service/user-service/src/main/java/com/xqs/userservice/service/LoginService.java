package com.xqs.userservice.service;

import com.xqs.commoncore.exception.MyException;
import com.xqs.userservice.request.LoginRequestDTO;
import com.xqs.userservice.response.LoginResponseDTO;

public interface LoginService {

    /**
     * 用户注册.
     * @param request
     * @return
     */
    LoginResponseDTO register(LoginRequestDTO request) throws MyException, InterruptedException;

    /**
     * 用户登录.
     * @param request
     * @return
     */
    LoginResponseDTO login(LoginRequestDTO request) throws MyException;
}
