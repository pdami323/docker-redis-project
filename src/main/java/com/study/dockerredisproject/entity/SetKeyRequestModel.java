package com.study.dockerredisproject.entity;

import lombok.Data;

@Data
public class SetKeyRequestModel {
    private String key;
    private String type;
    private String value;
    private int expireTime;
}
