package com.hoin.boardStudy.board.config;

import com.hoin.boardStudy.board.config.filter.LoginCheck;
import com.hoin.boardStudy.board.config.handler.PageRequestHandler;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.Filter;
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

    public FilterRegistrationBean loginCheck() {
        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new LoginCheck());
        filterRegistrationBean.setOrder(1);
        filterRegistrationBean.addUrlPatterns("/*");

        return filterRegistrationBean;
    }

}
