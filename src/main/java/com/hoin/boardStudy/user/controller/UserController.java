package com.hoin.boardStudy.user.controller;

import com.hoin.boardStudy.user.dto.User;
import com.hoin.boardStudy.user.service.LoginVerification;
import com.hoin.boardStudy.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final LoginVerification loginVerification;

    /* 로그인 화면 */
    @GetMapping("/login.do")
    public String login() {
        return "user/login";
    }

    /* 로그인 처리 */
    @PostMapping("/loginProcess.do")
    public String loginProcess(User user, HttpSession session) {
        String rawPassword = user.getPassword();
        if(loginVerification.loginVerification(user, rawPassword)) {
            session.setAttribute("user", user);
            return "redirect:/board/list.do";
        }
        return "redirect:/user/login.do";
    }

    /* 로그아웃 */
    @GetMapping("/logout.do")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/board/list.do";
    }

    /* 프로필 */
    @GetMapping("/profile.do")
    public String profile(HttpSession session) {
        String userId = ((User) session.getAttribute("user")).getUserId();
        return "user/profile";
    }

    /* 유저정보 조회 */
    @GetMapping("/getUserInfo.do")
    public String getUserInfo(HttpSession session, Model m) {
        String userId = ((User) session.getAttribute("user")).getUserId();
        m.addAttribute("user", userService.getUserInfo(userId));

        return "user/modify";
    }

    /* 유저정보 수정 */
    @PostMapping("/modify.do")
    public String modify(User user, HttpSession session) {
        String userId = ((User) session.getAttribute("user")).getUserId();
        user.setUserId((userId));
        userService.modifyUserInfo(user);

        return "redirect:/user/getUserInfo.do";
    }

    /* 회원가입 페이지*/
    @GetMapping("signUp.do")
    public String signUpForm() {

        return "user/signUp";
    }

    /* 회원가입 정보 저장 */

    @PostMapping("/signUp.do")
    public String joinUser(User user) {
        String rawPassword = user.getPassword();
        userService.joinUser(user, rawPassword);

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
