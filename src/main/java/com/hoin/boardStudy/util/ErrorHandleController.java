package com.hoin.boardStudy.util;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class ErrorHandleController implements ErrorController {

    private final String ERROR_404_PAGE_PATH = "error/404";
    private final String ERROR_500_PAGE_PATH = "error/500";
    private final String ERROR_ETC_PAGE_PATH = "error/error";

    @RequestMapping("error")
    public String handleError(HttpServletRequest request) {

        // 에러 코드 획득
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {
            int statusCode = Integer.valueOf(status.toString());

            // 404 ERROR
            if (statusCode == HttpStatus.NOT_FOUND.value()) return ERROR_404_PAGE_PATH;
            // 500 ERROR
            if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) return ERROR_500_PAGE_PATH;
        }
        // 그 외
        return ERROR_ETC_PAGE_PATH;
    }

}
