package com.hoin.boardStudy.board.config.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.PatternMatchUtils;

import javax.servlet.*;
import javax.servlet.Filter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;

@Component
@Slf4j
public class LoginCheck implements Filter {

    private static final String[] loginRequiredPath = {"/board/writeForm.do","/board/modify.do","/board/saveBoard.do","/board/delete.do","/user/logout.do","/user/profile.do","/user/getUserInfo.do","/user/modify.do","/user/withdraw.do"};
    private static final String LOGIN_URL = "/user/login.do";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        //HttpServletRequest, HttpServletResponse 기능을 이용하기 위해 다운캐스팅
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        String requestURI = httpRequest.getRequestURI();

        HttpSession session = httpRequest.getSession();

        if (!isLoginRequiredPath(requestURI)) {
            if (session == null || session.getAttribute("user") == null) {
                httpResponse.sendRedirect(httpRequest.getContextPath() + LOGIN_URL);
                return;
            }

        }

        // 다음 필터가 있으면 필터를 호출, 없으면 서블릿을 호출.
        chain.doFilter(request, response);
    }

    private boolean isLoginRequiredPath(String requestURI) {
        return !PatternMatchUtils.simpleMatch(loginRequiredPath, requestURI);
    }

}