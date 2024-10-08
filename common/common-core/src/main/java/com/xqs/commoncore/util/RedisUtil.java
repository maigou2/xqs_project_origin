package com.xqs.commoncore.util;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.ArrayList;

@Component
public class RedisUtil{

    //        if (request.getOperationType() == 0) {
    //            //加法
    //            operationCount = redisUtil.opsForValue().increment(request.getKey(), 1);
    //        } else if (request.getOperationType() == 1) {
    //            //减法
    //            operationCount = redisUtil.opsForValue().decrement(request.getKey(), 1);
    //        } else if (request.getOperationType() == 2) {
    //            //乘法
    //
    //        } else if (request.getOperationType() == 3) {
    //            //除法
    //
    //        }

    @Qualifier("redisTemplate")
    @Autowired
    private RedisTemplate template;

    public boolean hasKey(String key) {
        return template.hasKey(key);
    }

    //字符串操作
    public void set(String key, Object o) {
         template.opsForValue().set(key, o);
    }

    public void set(String key, Object o, long time) {
         template.opsForValue().set(key, o, time);
    }

    public void set(String key, Object o, Duration duration) {
         template.opsForValue().set(key, o, duration);
    }

    public boolean del(String key) {
        template.delete(key);
        return !hasKey(key);
    }

    public String getStr(String key) {
        if (!hasKey(key)) {
            return "";
        }
        return template.opsForValue().get(key).toString();
    }

    public Object get(String key) {
        if (!hasKey(key)) {
            return new ArrayList<>();
        }
        return template.opsForValue().get(key);
    }

    public long increment(String key, int value) {
        return template.opsForValue().increment(key, value);
    }

    public ValueOperations opsForValue() {
        return this.template.opsForValue();
    }

    public ListOperations opsForList() {
        return this.template.opsForList();
    }

    public SetOperations opsForSet() {
        return this.template.opsForSet();
    }

    public ZSetOperations opsForZSet() {
        return this.template.opsForZSet();
    }

    public HashOperations opsForHash() {
        return this.template.opsForHash();
    }

}
