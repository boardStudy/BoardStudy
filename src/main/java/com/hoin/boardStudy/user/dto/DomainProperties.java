package com.hoin.boardStudy.user.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("domain")
@Getter
@Setter
public class DomainProperties {

    private String name;
}
