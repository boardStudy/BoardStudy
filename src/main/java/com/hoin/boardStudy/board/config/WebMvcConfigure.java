package com.hoin.boardStudy.board.config;

import com.hoin.boardStudy.board.config.handler.PageRequestHandler;
import com.hoin.boardStudy.board.config.interceptor.LoginCheck;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
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

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginCheck())
                .order(1)
                .addPathPatterns("/**")
                .excludePathPatterns("/user/login.do","/user/loginProcess.do","/user/logout.do","/board/list.do","/board/detail.do");
    }
}
