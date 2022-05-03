package com.hoin.boardStudy.user.controller;

import com.hoin.boardStudy.user.dto.User;
import com.hoin.boardStudy.user.service.LoginVerification;
import com.hoin.boardStudy.user.service.RandomNumberManagement;
import com.hoin.boardStudy.user.service.UserService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Map;
import java.util.HashMap;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final LoginVerification loginVerification;

    private final JavaMailSender javaMailSender;

    private final RandomNumberManagement randomNumberManagement;

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
    public String profile(HttpSession session, Model m) {
        String userId = ((User) session.getAttribute("user")).getUserId();
        m.addAttribute("user", userService.getUserInfo(userId));
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
    public String signUpForm(User user) {

        return "user/signUp";
    }

    /* 회원가입 정보 저장 */

    @PostMapping("/signUp.do")
    public String joinUser(@Valid User user, Errors errors, Model m) {
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

    @ResponseBody
    @GetMapping("/mailCheck")
    public String mailCheck(@RequestParam("email") String email) throws Exception {
        // randomNumberManagement.randomNumber();
        int serti = (int)((Math.random() * (99999 - 10000 + 1)) + 10000);

        String from = "dahoonemailtest@gmail.com"; //보내는 이 메일주소
        String to = email;
        String title = "회원가입시 필요한 인증번호 입니다.";
        String content = "[인증번호] " + serti + " 입니다. <br/> 인증번호 확인란에 기입해주십시오.";
        String num = "";

        try {
            MimeMessage mail = javaMailSender.createMimeMessage();
            MimeMessageHelper mailHelper = new MimeMessageHelper(mail, true, "UTF-8");

            mailHelper.setFrom(from);
            mailHelper.setTo(to);
            mailHelper.setSubject(title);
            mailHelper.setText(content, true);

            javaMailSender.send(mail);
            num = Integer.toString(serti);
            System.out.println(serti);
        } catch (Exception e) {
            num = "error";
        }
        return num; // 인증번호
    }


}
