package com.cx.meetyoubackend.service;

import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class RedisCrudService {

  @Autowired
  private RedisTemplate<String, Object> redisTemplate;

  public void set(String key, Object value) {
    redisTemplate.opsForValue().set(key, value);
  }

  public void setWithExpireTime(String key, Object value, long timeout, TimeUnit unit) {
    redisTemplate.opsForValue().set(key, value, timeout, unit);
  }

  public Object get(String key) {
    return redisTemplate.opsForValue().get(key);
  }

  public void update(String key, Object value) {
    if (redisTemplate.hasKey(key)) {
      redisTemplate.opsForValue().set(key, value);
    }
  }

  public void delete(String key) {
    redisTemplate.delete(key);
  }
}

