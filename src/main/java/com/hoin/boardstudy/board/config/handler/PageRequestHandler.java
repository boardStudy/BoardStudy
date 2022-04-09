package com.hoin.boardstudy.board.config.handler;

import com.hoin.boardstudy.board.dto.PageInfo;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
public class PageRequestHandler implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().equals(PageInfo.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception{

        // null일 때, 초기값 설정
        Integer page = 1;
        Integer pageSize = 5;

        String parameterPage = webRequest.getParameter("page");
        String parameterPageSize = webRequest.getParameter("pageSize");

        if (parameterPage != null && parameterPageSize != null) {
            page = Integer.parseInt(webRequest.getParameter("page"));
            pageSize = Integer.parseInt(webRequest.getParameter("pageSize"));
        }

        return new PageInfo(page, pageSize);
    }
}
