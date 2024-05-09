package com.study.dockerredisproject.controller;

import com.study.dockerredisproject.config.RedisConfig;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/api/test")
@Tag(name = "02. 테스트 컨트롤러", description = "redis 컨트롤러")
public class TestController {

    @Autowired
    private RedisTemplate<String , String > redisTemplate;

    @PostMapping("/redisTest")
    public ResponseEntity<?> addRedisKey(){
        try {
            ValueOperations<String, String> vop = redisTemplate.opsForValue();
            vop.set("yellow", "banana");
        }catch (Exception e){
            log.info(e.getMessage());
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
