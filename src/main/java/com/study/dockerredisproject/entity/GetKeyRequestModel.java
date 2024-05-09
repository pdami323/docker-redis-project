package com.study.dockerredisproject.entity;

import lombok.Data;

@Data
public class GetKeyRequestModel {
    private String key;
    private String type;
    private String rowKey;
}
