package com.xqs.commoncore.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class RedisUtil {

    @Qualifier("redisTemplate")
    @Autowired
    private RedisTemplate template;

    public boolean hasKey(String key) {
        return template.hasKey(key);
    }

    public void set(String key, Object o) {
         template.opsForValue().set(key, o);
    }

    public void set(String key, Object o, long time) {
         template.opsForValue().set(key, o, time);
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

}
