package com.study.dockerredisproject.component.redis;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
@RequiredArgsConstructor
public class RedisStringsComponent<T> {

    private RedisTemplate<String, Object> redisTemplate;

    public void setValue(String key, T value, int expireTime){
        redisTemplate.opsForValue().set(key, value);
        redisTemplate.expire(key, expireTime, TimeUnit.MILLISECONDS);
    }

    public T getValue(String key, Class<T> Type){
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.convertValue(redisTemplate.opsForValue().get(key), Type);
    }

    public void deleteKey(String key) {
        redisTemplate.delete(key);
    }
}
