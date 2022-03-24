package com.hoin.boardStudy.board.mvc;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@ControllerAdvice
@Slf4j
@RequiredArgsConstructor
public class GlobalControllerAdvice {
    
    @ExceptionHandler(Exception.class)
    public Map handleException(Exception cause, ModelAndView mv) {
        log.error("{} Uncaught Exception : {}", cause.toString(), cause);
        return mv.getModel();
    }
}
