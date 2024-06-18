package com.xqs.userservice.service;

import com.github.pagehelper.PageInfo;
import com.xqs.userapi.info.UserInfo;
import com.xqs.userservice.request.redis.RedisTestRequestDTO;

public interface RedisTestService {
    void testRedisPushList(RedisTestRequestDTO request) throws InterruptedException;

    String testRedisPopList(RedisTestRequestDTO request);

    void testAddRedisMaxCount(RedisTestRequestDTO request);

    String testAddRedisAddOrSub(RedisTestRequestDTO request);

    String testRedisLock(RedisTestRequestDTO request) throws InterruptedException;

    Boolean delRedisByKey(String key);
}
