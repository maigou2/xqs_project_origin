package com.xqs.userservice.service;

import com.xqs.userservice.request.redis.RedisTestRequestDTO;

public interface RedisTestService {
    void testRedisPushList(RedisTestRequestDTO request) throws InterruptedException;

    void testRedisPopList(RedisTestRequestDTO request) throws InterruptedException;

    void testAddRedisMaxCount(RedisTestRequestDTO request);

    String testAddRedisAddOrSub(RedisTestRequestDTO request);
}
