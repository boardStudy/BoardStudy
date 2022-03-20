package com.hoin.boardStudy.user.controller;

import com.hoin.boardStudy.user.dto.User;
import com.hoin.boardStudy.user.service.PasswordManagement;
import com.hoin.boardStudy.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final PasswordManagement passwordManagement;
    /* 로그인 화면 */
    @GetMapping("/login.do")
    public String login() {
        return "user/login";
    }

    /* 로그인 처리 */
    @PostMapping("/login.do")
    public String login(User user, HttpServletRequest req) {
        String rawPassword = user.getPassword();
        user = userService.userCheck(user.getUserId()); // = null
        if (user != null) {
            // 유저 값이 있을 때
            userService.login(user, rawPassword);
            HttpSession session = req.getSession();
            session.setAttribute("user", user);
        }
        return "/index";
    }

    /* 로그아웃 */
    @GetMapping("/logout.do")
    public String logout(HttpSession session) {
        session.invalidate();

        return "redirect:/board/list.do";
    }

    /* 유저정보 체크 */
    @GetMapping("/userCheck.do")
    public String userCheck(HttpSession session, Model m) {
        String userId = ((User) session.getAttribute("user")).getUserId();
        m.addAttribute("user", userService.userCheck(userId));

        return "/";
    }

    /* 회원가입 페이지*/
    @GetMapping("/signUp.do")
    public String signUpForm() {

        return "user/signUp";
    }

    /* 회원가입 정보 저장 */
    @PostMapping("/signUp.do")
    public String joinUser(User user) {
        String encryptPassword = passwordManagement.encryptPassword(user);
        userService.joinUser(user);
        return "redirect:/user/login.do";
    }

    /* 탈퇴 */
    @GetMapping("/withdraw.do")
    public String withdraw(HttpSession session, Model m) {
        String userId = ((User) session.getAttribute("user")).getUserId();
        userService.withdrawUser(userId);
        session.invalidate();

        return "redirect:/board/list.do";
    }

}
