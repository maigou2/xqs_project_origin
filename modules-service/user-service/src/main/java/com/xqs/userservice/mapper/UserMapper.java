package com.xqs.userservice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xqs.userapi.info.UserInfo;
import com.xqs.userservice.request.LoginRequestDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<UserInfo> {

    void insertUserInfo(UserInfo userInfo);

    void addAllUsers(@Param("userInfos") List<UserInfo> userInfos);
}
