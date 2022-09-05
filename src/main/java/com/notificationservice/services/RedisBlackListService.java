package com.notificationservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class RedisBlackListService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    public void save(String key, String val) {
        redisTemplate.opsForValue().set(key, val);
    }

    public void deleteByKey(String key) {
        redisTemplate.delete(key);
    }

    public Boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }

    public Set<String> getKeys() {
        return redisTemplate.keys("*");
    }
}
