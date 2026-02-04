package com.example.util;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import jakarta.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Component
public class RedisUtil {
    
    @Resource
    private RedisTemplate<String, Object> redisTemplate;
    
    /**
     * 设置缓存
     */
    public void set(String key, Object value) {
        ValueOperations<String, Object> operations = redisTemplate.opsForValue();
        operations.set(key, value);
    }
    
    /**
     * 设置缓存（带过期时间）
     */
    public void set(String key, Object value, long timeout, TimeUnit unit) {
        ValueOperations<String, Object> operations = redisTemplate.opsForValue();
        operations.set(key, value, timeout, unit);
    }
    
    /**
     * 获取缓存
     */
    public Object get(String key) {
        ValueOperations<String, Object> operations = redisTemplate.opsForValue();
        return operations.get(key);
    }
    
    /**
     * 删除缓存
     */
    public void delete(String key) {
        redisTemplate.delete(key);
    }
    
    /**
     * 检查缓存是否存在
     */
    public boolean exists(String key) {
        return Boolean.TRUE.equals(redisTemplate.hasKey(key));
    }
    
    /**
     * 设置过期时间
     */
    public boolean expire(String key, long timeout, TimeUnit unit) {
        return Boolean.TRUE.equals(redisTemplate.expire(key, timeout, unit));
    }
    
    /**
     * 获取过期时间
     */
    public Long getExpire(String key) {
        return redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }
    
    /**
     * 自增
     */
    public Long increment(String key) {
        ValueOperations<String, Object> operations = redisTemplate.opsForValue();
        return operations.increment(key);
    }
    
    /**
     * 自增指定值
     */
    public Long incrementBy(String key, long delta) {
        ValueOperations<String, Object> operations = redisTemplate.opsForValue();
        return operations.increment(key, delta);
    }
    
    /**
     * 自减
     */
    public Long decrement(String key) {
        ValueOperations<String, Object> operations = redisTemplate.opsForValue();
        return operations.decrement(key);
    }
    
    /**
     * 自减指定值
     */
    public Long decrementBy(String key, long delta) {
        ValueOperations<String, Object> operations = redisTemplate.opsForValue();
        return operations.decrement(key, delta);
    }
}
