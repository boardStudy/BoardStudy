package com.hoin.boardStudy.util;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "upload")
@Getter
@Setter
public class FileConfig {
    private String Path;
}
