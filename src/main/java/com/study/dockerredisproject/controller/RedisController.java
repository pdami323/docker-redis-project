package com.study.dockerredisproject.controller;

import com.study.dockerredisproject.config.RedisConfig;
import com.study.dockerredisproject.entity.GetKeyRequestModel;
import com.study.dockerredisproject.entity.SetKeyRequestModel;
import com.study.dockerredisproject.service.RedisService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Slf4j
@RequestMapping("/api/redis")
@Tag(name = "01. 레디스 컨트롤러", description = "redis 컨트롤러")
@RequiredArgsConstructor
public class RedisController {

    private final RedisConfig redisConfig;

    private final RedisService redisService;

    @Operation(summary = "key 가져오기")
    @Parameters({
            @Parameter(name = "key", description = "key", required = true, in = ParameterIn.PATH,
                    schema = @Schema(type = "string")),
            @Parameter(name = "type", description = "String or Hash", required = true, in=ParameterIn.QUERY,
                    schema = @Schema(type = "string"))
    })
    @GetMapping("/{key}")
    public ResponseEntity<Object> getValue(@Valid @Parameter(hidden = true)GetKeyRequestModel getKeyRequestModel){
        Object result = null;
        try {
            result = redisService.getData(getKeyRequestModel);
        } catch (Exception e){
            log.info(e.getMessage());
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @Operation(summary = "데이터 저장")
    @Parameters({
            @Parameter(name = "key", description = "key", required = true, in=ParameterIn.PATH,
            schema = @Schema(type = "string")),
            @Parameter(name = "value", description = "value", required = true, in=ParameterIn.QUERY,
            schema = @Schema(type = "string")),
            @Parameter(name = "type", description = "type", required = true, in=ParameterIn.QUERY),
            @Parameter(name = "expireTime", description = "expireTime", required = true, in=ParameterIn.QUERY,
            schema = @Schema(type = "integer"))
    })
    @PostMapping("/{key}")
    public ResponseEntity<Void> setValue(@Valid @Parameter(hidden = true)SetKeyRequestModel setKeyRequestModel){
        try {
            redisService.setData(setKeyRequestModel);
        }catch (Exception e){
            log.info(e.getMessage());
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
