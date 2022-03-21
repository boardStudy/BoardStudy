package com.hoin.boardStudy.board.config.handler;

import com.hoin.boardStudy.board.dto.PageInfo;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class PageRequestHandler implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().equals(PageInfo.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception{

        String page = webRequest.getParameter("page");
        String pageSize = webRequest.getParameter("pageSize");

        // 기본값 설정
        if (page == null) page = "1";
        if (pageSize == null) pageSize = "5";

        return new PageInfo(page, pageSize);
    }
}
