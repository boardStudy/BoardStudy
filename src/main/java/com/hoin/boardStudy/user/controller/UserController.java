package com.hoin.boardStudy.user.controller;

import com.hoin.boardStudy.user.dto.User;
import com.hoin.boardStudy.user.service.PasswordManagement;
import com.hoin.boardStudy.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final PasswordManagement passwordManagement;

    // final 초기화를 해줘야하기 때문에 생성자 필요 -> RequiredAG

    /* 회원가입 페이지*/
    @GetMapping("signUp.do")
    public String signUpForm() {

        return "user/signUp";
    }

    /* 회원가입 정보 저장 */
    @PostMapping("signUp.do")
    public String signUp(User user) {
        passwordManagement.encryptPassword(user);
        userService.joinUser(user);
        return "redirect:/user/login.do";
    }


    /* 로그인 페이지 */
    @GetMapping("login.do")
    public String login() {
        System.out.println("login getController called");
        return "user/login";
    }

    /* 로그인 및 세션 */
    @PostMapping("login.do")
    public String login(User user, HttpServletRequest req, RedirectAttributes rttr) throws Exception {
        HttpSession session = req.getSession();
        User login = userService.login(user);

        if (login == null) {
            session.setAttribute("user", null);
            rttr.addFlashAttribute("message", false);
        } else {
            session.setAttribute("user", login);
        }
        return "redirect:/board/list.do";
    }

    /* 로그아웃 */
    @GetMapping("logout.do")
    public String logout(HttpSession session) {
        System.out.println("logout getController called");
        session.invalidate();

        return "redirect:/board/list.do";
    }

}
