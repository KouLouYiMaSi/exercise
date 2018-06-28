package com.huo.demos.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisDemo {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public RedisUtil getRedisUtil() {
        RedisUtil ru = new RedisUtil();
        ru.setRedisTemplate(stringRedisTemplate);
        return ru;
    }

}
