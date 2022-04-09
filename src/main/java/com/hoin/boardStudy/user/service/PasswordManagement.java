package com.hoin.boardStudy.user.service;

import com.hoin.boardStudy.user.dto.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PasswordManagement {

    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public String encryptPassword(User user) {
        // 유저라는 객체를 받아와서
        String rawPassword = user.getPassword();
        // 패스워드를 받아와서 rawPassword 변수를 선언.
        return passwordEncoder.encode(rawPassword);
        // 암호화 된 비밀번호를 리턴한다.

    }
}
