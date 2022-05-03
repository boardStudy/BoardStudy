package com.hoin.boardStudy.util;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "spring.redis")
@Getter
@Setter
public class RedisConfig {
    private String host;
    private int port;
}
