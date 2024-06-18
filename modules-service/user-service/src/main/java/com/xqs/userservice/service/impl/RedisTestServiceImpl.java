package com.xqs.userservice.service.impl;

import com.github.pagehelper.PageInfo;
import com.xqs.commoncore.exception.MyException;
import com.xqs.commoncore.util.ObjectUtil;
import com.xqs.commoncore.util.RedisUtil;
import com.xqs.commoncore.util.ThreadUtil;
import com.xqs.userapi.info.UserInfo;
import com.xqs.userservice.request.redis.RedisTestRequestDTO;
import com.xqs.userservice.request.user.UserInfoRequestDTO;
import com.xqs.userservice.service.RedisTestService;
import com.xqs.userservice.service.UserService;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;

@Service
public class RedisTestServiceImpl implements RedisTestService {

    @Resource
    private ExecutorService executorService;

    @Resource
    private RedisUtil redisUtil;

    @Resource
    private UserService userService;

    @Override
    public void testRedisPushList(RedisTestRequestDTO request){
        UserInfoRequestDTO userInfoRequestDTO = new UserInfoRequestDTO();
        userInfoRequestDTO.setPageNum(1);
        userInfoRequestDTO.setPageSize(50);
        PageInfo<UserInfo> userInfos = userService.getAllUserByPage(userInfoRequestDTO);
        redisUtil.opsForList().leftPushAll(request.getKey(), userInfos.getList());
    }

    @Override
    public String testRedisPopList(RedisTestRequestDTO request){
//        List<UserInfo> userInfos = redisUtil.opsForList().range("userInfosList", 0, 1000);
//        redisUtil.opsForList().remove("userInfosList", 0, 1000);
        return "";
    }

    @Override
    public void testAddRedisMaxCount(RedisTestRequestDTO request) {
        redisUtil.opsForValue().set(request.getKey(), request.getMaxValue());
    }

    @Override
    public String testAddRedisAddOrSub(RedisTestRequestDTO request) {

        Object o = redisUtil.opsForValue().get(request.getKey());

        if (StringUtils.isEmpty(o)) {
            return "没有当前商品数据";
        }

        Integer count = (Integer) o;
        if (count < 1) {
            return "当前商品已售完";
        }

        Long surplus = redisUtil.opsForValue().decrement(request.getKey(), 1);

        if(surplus < 0) {
            redisUtil.opsForValue().increment(request.getKey(), 1);
            return "当前商品已售完";
        }
        return "购买成功";
    }

    @Override
    public String testRedisLock(RedisTestRequestDTO request) throws InterruptedException {
        String result = redisUtil.opsForValue().setIfAbsent(request.getKey(), ObjectUtil.getUUID(), Duration.ofSeconds(5)) ? "获取锁成功" : "获取锁失败";
        return result;
    }

    @Override
    public Boolean delRedisByKey(String key) {
        return redisUtil.del(key);
    }

    private void redisPushLeftList(CountDownLatch latch, String key, String value) {
        redisUtil.opsForList().leftPush(key, value);
        latch.countDown();
    }

    private void redisGetLeftList(String key, int index, List<String> pulledData) {
        Object o = redisUtil.opsForList().leftPop(key);
        if (StringUtils.isEmpty(o)) {
            return;
        }
        if (pulledData.contains(o.toString())) {
            System.out.println("第" + index + "次循环,获取到的数据是:" + o + "|获取到重复数据");
        }
        System.out.println("第" + index + "次循环,获取到的数据是:" + o);
    }
}
