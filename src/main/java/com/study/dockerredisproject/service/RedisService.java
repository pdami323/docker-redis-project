//package com.study.dockerredisproject.service;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.stereotype.Service;
//
//import java.util.concurrent.TimeUnit;
//
//@Service
//@Slf4j
//public class RedisService {
//
//    private final RedisTemplate<String, Object> redisTemplate;
//
//    public RedisService(RedisTemplate<String, Object> redisTemplate) {
//        this.redisTemplate = redisTemplate;
//    }
//
//    public void setDatawithExpired(String key, String value, Long expiredTime){
//        redisTemplate.opsForValue().set(key, value, expiredTime, TimeUnit.MILLISECONDS);
//    }
//
//    public void setData(String key, String value){
//        redisTemplate.opsForValue().set(key, value);
//    }
//
//    public String getData(String key){
//        return (String) redisTemplate.opsForValue().get(key);
//    }
//
//    public void deleteData(String key){
//        redisTemplate.delete(key);
//    }
//}
