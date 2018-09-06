package com.company.project.core.util;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @Package com.company.project.core.util
 * @Description
 * @Project spring-boot-ssm
 * @Author caoxile
 * @Create 2018-09-06
 */
@Component
public class RedisUtil {

    @Resource
    private RedisTemplate<String,Object> redisTemplate;

    //设置变量
    public void set(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    //设置变量(过期时间,单位:秒)
    public void set(String key, Object value, long expireSeconds) {
        redisTemplate.opsForValue().set(key, value, expireSeconds, TimeUnit.SECONDS);
    }

    //获取变量值
    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    //设置过期时间(单位:秒)
    public void expire(String key, long expireSeconds) {
        redisTemplate.expire(key,expireSeconds,TimeUnit.SECONDS);
    }

    //删除变量
    public boolean delete(String key) {
        return redisTemplate.delete(key);
    }

    //判断缓存中是否有对应的value
    public boolean exists(String key) {
        return redisTemplate.hasKey(key);
    }
}
