package com.study.dockerredisproject.controller;

import com.study.dockerredisproject.config.RedisConfig;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/api/redis")
@Tag(name = "레디스 컨트롤러", description = "redis 컨트롤러")
public class RedisController {

    @Autowired
    private RedisConfig redisConfig;

    @GetMapping("/{key}")
    public String getValue(@PathVariable String key){
        RedisTemplate<String, String> redisTemplate = redisConfig.redisTemplate();
        return (String) redisTemplate.opsForValue().get(key);
//        return redisService.getData(key);
    }

//    @PostMapping("/{key}")
//    public void setValue(@PathVariable String key, String value){
//        redisService.setData(key, value);
//    }

}
