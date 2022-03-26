package com.hoin.boardStudy.user.service;

import com.hoin.boardStudy.user.dto.User;
import com.hoin.boardStudy.user.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginVerification {

    public final PasswordManagement passwordManagement;
    public final UserService userService;
    public final UserMapper userMapper;
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    /* 로그인 검증 */
    public boolean loginVerification(User user, String rawPassword) {
        user = userService.getUserInfo(user.getUserId());
        if (user != null &&
                passwordEncoder.matches(rawPassword, user.getPassword())) {
            return true;
        }
            return false;
    }

}