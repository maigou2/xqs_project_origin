package com.xqs.userservice.request.redis;

import lombok.Data;

@Data
public class RedisTestRequestDTO {

    private Integer operationType;

    private Integer redisDataType;

    private String key;

    private String value;

    private Integer maxValue;


}
