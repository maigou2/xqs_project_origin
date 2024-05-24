package com.xqs.userservice.service.impl;

import com.xqs.userservice.request.redis.RedisTestRequestDTO;
import com.xqs.userservice.service.RedisTestService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.lang.model.type.IntersectionType;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;

@Service
public class RedisTestServiceImpl implements RedisTestService {

    @Resource
    private ExecutorService executorService;

    @Resource
    private RedisTemplate redisTemplate;

    @Override
    public void testRedisPushList(RedisTestRequestDTO request) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(request.getMaxValue());
        for (int i = 1; i <= request.getMaxValue(); i++) {
            int finalI = i;
            executorService.submit(() -> {
                this.redisPushLeftList(latch, request.getKey(), "第" + finalI + "个元素");
            });
        }
        latch.await();
    }

    @Override
    public void testRedisPopList(RedisTestRequestDTO request) throws InterruptedException {
        List<String> pulledData = new ArrayList<>();
        for (int i = 1; i <= request.getMaxValue(); i++) {
            int finalI = i;
            int finalI1 = i;
            if(!redisTemplate.hasKey(request.getKey())) {
                System.out.println("已经取完了");
                break;
            }
            executorService.submit(() -> {
                this.redisGetLeftList(request.getKey(), finalI1, pulledData);
            });
        }
    }

    @Override
    public void testAddRedisMaxCount(RedisTestRequestDTO request) {
        redisTemplate.opsForValue().set(request.getKey(), request.getMaxValue());
    }

    @Override
    public String testAddRedisAddOrSub(RedisTestRequestDTO request) {

        Object o = redisTemplate.opsForValue().get(request.getKey());

        if (StringUtils.isEmpty(o)) {
            return "没有当前商品数据";
        }

        Integer count = (Integer) o;
        if (count < 1) {
            return "当前商品已售完";
        }

        Long surplus = redisTemplate.opsForValue().decrement(request.getKey(), 1);

        if(surplus < 0) {
            redisTemplate.opsForValue().increment(request.getKey(), 1);
            return "当前商品已售完";
        }

//        if (request.getOperationType() == 0) {
//            //加法
//            operationCount = redisTemplate.opsForValue().increment(request.getKey(), 1);
//        } else if (request.getOperationType() == 1) {
//            //减法
//            operationCount = redisTemplate.opsForValue().decrement(request.getKey(), 1);
//        } else if (request.getOperationType() == 2) {
//            //乘法
//
//        } else if (request.getOperationType() == 3) {
//            //除法
//
//        }
//        redisTemplate.opsForValue().get(request.getKey()).toString();

        return "购买成功";
    }

    private void redisPushLeftList(CountDownLatch latch, String key, String value) {
        redisTemplate.opsForList().leftPush(key, value);
        latch.countDown();
    }

    private void redisGetLeftList(String key, int index, List<String> pulledData) {
        Object o = redisTemplate.opsForList().leftPop(key);
        if (StringUtils.isEmpty(o)) {
            return;
        }
        if (pulledData.contains(o.toString())) {
            System.out.println("第" + index + "次循环,获取到的数据是:" + o + "|获取到重复数据");
        }
        System.out.println("第" + index + "次循环,获取到的数据是:" + o);
    }
}
