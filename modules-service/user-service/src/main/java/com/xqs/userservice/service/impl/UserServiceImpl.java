package com.xqs.userservice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xqs.userapi.info.UserInfo;
import com.xqs.userservice.mapper.UserMapper;
import com.xqs.userservice.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserInfo> implements UserService{

    @Resource
    private UserMapper userMapper;

    @Override
    public List<UserInfo> getAllUserList() {
        return this.list();
    }
}
