package com.xqs.userservice.service.impl;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.NumberUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.xqs.commoncore.constens.Constant;
import com.xqs.commoncore.exception.MyException;
import com.xqs.commoncore.util.Md5Util;
import com.xqs.commoncore.util.ObjectUtil;
import com.xqs.commoncore.util.RedisUtil;
import com.xqs.commoncore.util.StrRedisUtil;
import com.xqs.userapi.info.UserInfo;
import com.xqs.userservice.request.LoginRequestDTO;
import com.xqs.userservice.response.LoginResponseDTO;
import com.xqs.userservice.service.LoginService;
import com.xqs.userservice.service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.time.Duration;
import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;

@Service
public class LoginServiceImpl implements LoginService {

    @Resource
    private UserService userService;

    @Resource
    private RedisUtil redisUtil;

    @Resource
    private StrRedisUtil strRedisUtil;

    @Resource
    private ExecutorService executorService;

    @Override
    public LoginResponseDTO register(LoginRequestDTO request) throws MyException, InterruptedException {
        try{
            if (StringUtils.isEmpty(request.getEmail())) {
                throw new MyException("请填写完整信息");
            }
            UserInfo user = userService.getUserByEmail(request.getEmail());
            if (!StringUtils.isEmpty(user)) {
                throw new MyException("此邮箱已被注册,请直接登录");
            }
            request.setObjectId(getIdByDay("user_id"));;
//            registerRedis(request);
            executorService.submit(() -> {
                registerDB(request);
            });
        }catch (Exception e) {
            e.printStackTrace();
        }
        return new LoginResponseDTO(true);
    }

    private void registerRedis(LoginRequestDTO request) {
        UserInfo userInfo = new UserInfo();
        String passwordMd5 = Md5Util.getMd5(request.getPassword() + request.getEmail());
        userInfo.setId(request.getObjectId());
        userInfo.setCode(ObjectUtil.getUUID());
        userInfo.setUserName(request.getUserName());
        userInfo.setUserEmail(request.getEmail());
        userInfo.setUserPassword(passwordMd5);
        userInfo.setUserType(request.getUserType());
        userInfo.setUserPhone(request.getPhone());
        redisUtil.opsForList().leftPush("userInfosList", userInfo);
    }


    @Override
    public LoginResponseDTO login(LoginRequestDTO request) throws MyException {
        UserInfo user = userService.getUserByEmail(request.getEmail());
        if (ObjectUtils.isEmpty(user)) {
            throw new MyException("用户名或密码错误");
        }
        String postMd5 = Md5Util.getMd5(request.getPassword() + request.getEmail());
        if (!user.getUserPassword().equals(postMd5)) {
            throw new MyException("用户名或密码错误");
        }
        Map<String, Object> infos = new HashMap<>();
        infos.put("id", user.getId());
        infos.put("userName", user.getUserName());
        infos.put("userPhone", user.getUserPhone());
        infos.put("userAddr", user.getUserAddr());
        infos.put("loginTime", System.currentTimeMillis());
        infos.put("expireTime", 30);
        String jwtToken = Jwts.builder().addClaims(infos).signWith(SignatureAlgorithm.HS256, Constant.SIGNING_KEY)
                .compact();
        return new LoginResponseDTO(true, "登录成功", jwtToken, user);
    }


    private void registerDB(LoginRequestDTO request) {
        userService.insertUserInfo(request);
    }

    /**
     * 获取不重复的id.
     */
    public long getIdByDay(String key) {
        String id = "";
        String userIdKey = "userId_keyIndex";
        try {
            String ymd = DateUtil.format(new Date(), Constant.DATE_FORMAT_YMD) + "_" + key;
            long indexL = 1;
            if(redisUtil.opsForValue().setIfAbsent(ymd, "1", Duration.ofDays(1))) {
                //当前不存在这个key
                strRedisUtil.intValueSet(userIdKey, "1", Duration.ofDays(1));
            }else {
                indexL = strRedisUtil.increment(userIdKey, 1);
            }
            String l = (System.currentTimeMillis() + "").substring(4);
            String indexStr = indexL + "";
            int lost0 = 7 - indexStr.length();
            for (int i = 0; i < lost0; i++) {
                indexStr = "0" + indexStr;
            }
            id = l + indexStr;
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            return Long.parseLong(id);
        }

    }

}
