package com.xqs.userservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xqs.commoncore.util.Md5Util;
import com.xqs.commoncore.util.ObjectUtil;
import com.xqs.userapi.info.UserInfo;
import com.xqs.userservice.mapper.UserMapper;
import com.xqs.userservice.request.LoginRequestDTO;
import com.xqs.userservice.request.user.UserInfoRequestDTO;
import com.xqs.userservice.response.LoginResponseDTO;
import com.xqs.userservice.service.UserService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.ExecutorService;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserInfo> implements UserService {

    @Resource
    private UserMapper userMapper;


    @Override
    public void insertUserInfo(LoginRequestDTO request) {
        UserInfo userInfo = buildRegisterUserInfo(request);
        userMapper.insertUserInfo(userInfo);
    }

    private UserInfo buildRegisterUserInfo(LoginRequestDTO request) {
        UserInfo userInfo = new UserInfo();
        String passwordMd5 = Md5Util.getMd5(request.getPassword() + request.getEmail());
        userInfo.setId(request.getObjectId());
        userInfo.setCode(ObjectUtil.getUUID());
        userInfo.setUserName(request.getUserName());
        userInfo.setUserEmail(request.getEmail());
        userInfo.setUserPassword(passwordMd5);
        userInfo.setUserType(request.getUserType());
        userInfo.setUserPhone(request.getPhone());
        return userInfo;
    }

    @Override
    public UserInfo getUserByEmail(String email) {
        LambdaQueryWrapper<UserInfo> lw = new LambdaQueryWrapper<>();
        lw.eq(UserInfo::getUserEmail, email)
                .eq(UserInfo::getStatusType, 1);
        return this.getOne(lw);
    }

    @Override
    public PageInfo<UserInfo> getAllUserByPage(UserInfoRequestDTO request) {
        PageHelper.startPage(request.getPageNum(), request.getPageSize());
        PageInfo<UserInfo> info = new PageInfo<>(this.list());
        return info;
    }

    @Override
    public void addAllUsers(List<UserInfo> userInfos) {
        userMapper.addAllUsers(userInfos);
    }
}
