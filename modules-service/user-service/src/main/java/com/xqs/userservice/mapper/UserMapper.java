package com.xqs.userservice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xqs.userapi.info.UserInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<UserInfo> {
}
