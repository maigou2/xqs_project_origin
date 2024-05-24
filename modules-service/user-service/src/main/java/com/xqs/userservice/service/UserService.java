package com.xqs.userservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xqs.userapi.info.UserInfo;
import com.xqs.userservice.request.LoginRequestDTO;
import com.xqs.userservice.response.LoginResponseDTO;

import java.util.List;

public interface UserService extends IService<UserInfo> {

    List<UserInfo> getAllUserList();

    /**
     * 用户注册.
     * @param request
     * @return
     */
    LoginResponseDTO register(LoginRequestDTO request);
}
