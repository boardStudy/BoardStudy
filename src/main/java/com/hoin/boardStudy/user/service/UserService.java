package com.hoin.boardStudy.user.service;
import com.hoin.boardStudy.user.dto.User;
import com.hoin.boardStudy.user.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
<<<<<<< Updated upstream
=======

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
>>>>>>> Stashed changes

@Service
@RequiredArgsConstructor
public class UserService {

     public final UserMapper userMapper;
     BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
     private final PasswordManagement passwordManagement;
<<<<<<< Updated upstream

     /* 유저정보 조회 */
     public User getUserInfo(String userId) {
          return userMapper.getUserInfo(userId);
=======

     /* 로그인 */
     public boolean loginVerification(User user, HttpSession session, String rawPassword) {
          String encryptPassword = user.getPassword();

          if (passwordEncoder.matches(rawPassword, encryptPassword)) {
               session.setAttribute("user", user);
               return true;
          }
               return false;
     }

     /* 유저정보 체크 */
     public User userCheck(String userId) {
          return userMapper.userCheck(userId);
>>>>>>> Stashed changes
     }

     /* 회원가입 */
     public boolean joinUser(User user, String rawPassword) {
          String encryptPassword = passwordManagement.encryptPassword(user);
          user.setPassword(encryptPassword);
          userMapper.saveUser(user);

          return true;
     }

     /* 유저 탈퇴 */
     public void withdrawUser(String userId) {
          userMapper.withdrawUser(userId);
     }

}
