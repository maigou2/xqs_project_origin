package com.xqs.userservice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xqs.userapi.info.UserInfo;

import java.util.List;

public interface UserService extends IService<UserInfo> {

    List<UserInfo> getAllUserList();
}
