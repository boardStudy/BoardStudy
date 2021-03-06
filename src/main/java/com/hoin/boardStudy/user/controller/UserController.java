package com.hoin.boardStudy.user.controller;

import com.hoin.boardStudy.user.dto.User;
import com.hoin.boardStudy.user.service.EmailManagement;
import com.hoin.boardStudy.user.service.LoginVerification;
import com.hoin.boardStudy.user.service.RandomNumberManagement;
import com.hoin.boardStudy.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Map;

import static com.hoin.boardStudy.user.dto.EmailProperties.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final LoginVerification loginVerification;
    // private final EmailManagement emailManagement;

    // 로그인 화면
    @GetMapping("/login.do")
    public String login() {
        return "user/login";
    }

    // 로그인 처리
    @PostMapping("/loginProcess.do")
    public String loginProcess(User user, HttpSession session, Model m) {
        String rawPassword = user.getPassword();
        user = userService.getUserInfo(user.getUserId());
        if(loginVerification.loginVerification(user, rawPassword)) {
            if(user != null && user.getUserAuth() == 0) {
                m.addAttribute("Auth", user.getUserAuth());
                return "redirect:/user/registerNotCertified.do";
            }
            session.setAttribute("user", user);
            return "redirect:/board/list.do";
        }

        return "redirect:/user/login.do";
    }

    @GetMapping("/registerNotCertified.do")
    public String registerNotCertified() {
        return "user/registerNotCertified";
    }

    // 로그아웃
    @GetMapping("/logout.do")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/board/list.do";
    }

    // 프로필
    @GetMapping("/profile.do")
    public String profile(HttpSession session, Model m) {
        String userId = ((User) session.getAttribute("user")).getUserId();
        m.addAttribute("user", userService.getUserInfo(userId));
        return "user/profile";
    }

    // 유저정보 조회
    @GetMapping("/getUserInfo.do")
    public String getUserInfo(HttpSession session, Model m) {
        String userId = ((User) session.getAttribute("user")).getUserId();
        m.addAttribute("user", userService.getUserInfo(userId));

        return "user/modify";
    }

    // 유저정보 수정
    @PostMapping("/modify.do")
    public String modify(User user, HttpSession session) {
        String userId = ((User) session.getAttribute("user")).getUserId();
        user.setUserId((userId));
        userService.modifyUserInfo(user);

        return "redirect:/user/getUserInfo.do";
    }

    // 회원가입 페이지
    @GetMapping("signUp.do")
    public String signUpForm(User user) {

        return "user/signUp";
    }

    // 회원가입 정보 저장
    @PostMapping("/signUp.do")
    public String joinUser(@Valid User user, Errors errors, Model m) throws Exception {
        if(errors.hasErrors()) {
            // 회원가입 실패 시, 입력 데이터 유지
            m.addAttribute("user", user);

            // 유효성을 통과 못한 필드와 메세지를 핸들링
            Map<String, String> validatorResult = userService.validateHandling(errors);
            for (String key : validatorResult.keySet()) {
                m.addAttribute(key, validatorResult.get(key));
            }
            return "user/signUp";
        }

        // 회원가입 저장
        String rawPassword = user.getPassword();
        userService.joinUser(user, rawPassword);

        return "redirect:/user/successSignUp.do";
    }

    @GetMapping("/successSignUp.do")
    public String successSignUp() {
        return "user/successSignUp";
    }

    @GetMapping("/registerEmail")
    public String emailConfirm(String email, Model m) throws Exception {
        userService.userAuth(email);
        m.addAttribute("email", email);
        return "user/registerEmail";
    }

    // 아이디 중복 체크
    @PostMapping("/userIdCheck")
    @ResponseBody
    public int emailCheck(@RequestParam("userId") String userId){

        int cnt = userService.userIdCheck(userId);
        return cnt;
    }

    // 전화번호 체크
    @PostMapping("/phoneCheck")
    @ResponseBody
    public int phoneCheck(@RequestParam("phone") String phone){

        int cnt = userService.phoneCheck(phone);
        return cnt;
    }

    // 회원 탈퇴
    @GetMapping("/withdraw.do")
    public String withdraw(HttpSession session, Model m) {
        String userId = ((User) session.getAttribute("user")).getUserId();
        userService.withdrawUser(userId);
        session.invalidate();

        return "redirect:/board/list.do";
    }

//    // 이메일 전송
//    @ResponseBody
//    @GetMapping("/mailCheck")
//    public void mailCheck(@RequestParam("email") String email) throws Exception {
////        emailManagement.sendMail(email);
//    }

}
