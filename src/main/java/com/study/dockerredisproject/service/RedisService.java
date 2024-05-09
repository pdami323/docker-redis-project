package com.study.dockerredisproject.service;

import com.study.dockerredisproject.component.redis.RedisStringsComponent;
import com.study.dockerredisproject.entity.GetKeyRequestModel;
import com.study.dockerredisproject.entity.SetKeyRequestModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
@RequiredArgsConstructor
public class RedisService {

    private final RedisStringsComponent<Object> redisStringsComponent;

    private RedisTemplate<String, Object> redisTemplate;

    public void setDatawithExpired(String key, String value, Long expiredTime){
        redisTemplate.opsForValue().set(key, value, expiredTime, TimeUnit.MILLISECONDS);
    }

    public void setData(SetKeyRequestModel setKeyRequestModel){
        switch (setKeyRequestModel.getType()){
            case "String" -> redisStringsComponent.setValue(setKeyRequestModel.getKey(), setKeyRequestModel.getValue()
                    , setKeyRequestModel.getExpireTime());
            default -> {}
        }
    }

    public Object getData(GetKeyRequestModel getKeyRequestModel){
        return switch (getKeyRequestModel.getType()){
            case "String" -> redisStringsComponent.getValue(getKeyRequestModel.getKey(), Object.class);
            default -> null;
        };
    }

    public void deleteData(String key){
        redisTemplate.delete(key);
    }

}
