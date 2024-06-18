package com.xqs.userservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.xqs.userapi.info.UserInfo;
import com.xqs.userservice.request.LoginRequestDTO;
import com.xqs.userservice.request.user.UserInfoRequestDTO;
import com.xqs.userservice.response.LoginResponseDTO;

import java.util.List;

public interface UserService extends IService<UserInfo> {

    /**
     * 新增单个用户.
     * @param request
     */
    void insertUserInfo(LoginRequestDTO request);

    /**
     * 按Email查询单个用户.
     * @param email
     */
    UserInfo getUserByEmail(String email);

    /**
     * 按条件查询用户信息.
     * @param userInfoRequestDTO
     * @return
     */
    PageInfo<UserInfo> getAllUserByPage(UserInfoRequestDTO userInfoRequestDTO);

    /**
     * 用户信息.
     * @param userInfos
     */
    void addAllUsers(List<UserInfo> userInfos);
}
