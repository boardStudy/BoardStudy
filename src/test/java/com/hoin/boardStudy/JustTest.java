package com.hoin.boardStudy;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.hoin.boardStudy.user.dto.User;
import com.hoin.boardStudy.user.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@SpringBootTest
public class JustTest {


    @Autowired
    UserService userService;

    @Test
    void test() {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        String rawPassword = "1234";
        String encryptPassword = passwordEncoder.encode(rawPassword);

        System.out.println("encryptPassword : " + encryptPassword);


        boolean matches = passwordEncoder.matches(rawPassword, encryptPassword);

        System.out.println("is match : " + matches);
    }

    @Test
    void matchTest() {
        User 이호인 = userService.userCheck("민동현");

        boolean login = userService.login(이호인, "1234");

        System.out.println("login : " + login);
    }
}
