package com.hoin.boardstudy.board.config;

import com.hoin.boardstudy.board.config.handler.PageRequestHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebMvcConfigure implements WebMvcConfigurer {
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(PageRequestHandler());
    }

    public PageRequestHandler PageRequestHandler() {
        return new PageRequestHandler();
    }
}
