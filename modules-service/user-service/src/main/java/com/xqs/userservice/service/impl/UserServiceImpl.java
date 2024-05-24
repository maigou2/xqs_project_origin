package com.xqs.userservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xqs.userapi.info.UserInfo;
import com.xqs.userservice.mapper.UserMapper;
import com.xqs.userservice.request.LoginRequestDTO;
import com.xqs.userservice.response.LoginResponseDTO;
import com.xqs.userservice.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserInfo> implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public List<UserInfo> getAllUserList() {
        return this.list();
    }

    @Override
    public LoginResponseDTO register(LoginRequestDTO request) {
        LambdaQueryWrapper<UserInfo> lw = new LambdaQueryWrapper<>();
        lw.eq(UserInfo::getUserEmail, request.getEmail())
                .eq(UserInfo::getStatusType, 1);
        UserInfo one = this.getOne(lw);
        if (!StringUtils.isEmpty(one)) {
            return new LoginResponseDTO(false, "邮箱已使用");
        }
        userMapper.insertUserInfo(request);
        return new LoginResponseDTO(true);
    }
}
