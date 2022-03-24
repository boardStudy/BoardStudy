package com.hoin.boardStudy.board.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class TestC {
    
    @GetMapping("/test")
    public void test() {
        log.error("에러");
        throw new RuntimeException("에러쓰");
    }
}
