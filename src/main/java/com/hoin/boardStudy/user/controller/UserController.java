package com.hoin.boardStudy.user.controller;

import com.hoin.boardStudy.user.dto.User;
import com.hoin.boardStudy.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    /* 회원가입 페이지*/
    @GetMapping("/signUp.do")
    public String signUpForm() {
        return "user/signUp";
    }

    /* 회원가입 정보 저장 */
    @PostMapping("/signUp.do")
    public String signUp(User user) {
        userService.joinUser(user);
        return "redirect:/user/login.do";
    }

    /* 로그인 */
    @GetMapping("/login.do")
    public String login() {
        return "user/login";
    }
    
}
