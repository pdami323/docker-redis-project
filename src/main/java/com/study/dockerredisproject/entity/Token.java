package com.study.dockerredisproject.entity;

import lombok.Builder;
import lombok.Data;
import nonapi.io.github.classgraph.json.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

@Data
@RedisHash("token")
public class Token {
    @Id
    Long user_id;

    String token;

    @TimeToLive
    Integer expiration;

    @Builder
    public Token(Long user_id, String token, Integer expiration) {
        this.user_id = user_id;
        this.token = token;
        this.expiration = expiration;
    }
}
