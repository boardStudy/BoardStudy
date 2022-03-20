package com.hoin.boardStudy.user.service;
import com.hoin.boardStudy.user.dto.User;
import com.hoin.boardStudy.user.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

     public final UserMapper userMapper;
     BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


     /* 로그인 */
     public boolean login(User user, String rawPassword) {
          String encryptPassword = user.getPassword();
          if(passwordEncoder.matches(rawPassword, encryptPassword)) {
               return true;
          }
               return false;
     }

     /* 유저정보 체크 */
     public User userCheck(String userId) {
          return userMapper.userCheck(userId);
     }
     // 로그인할 유저가 없으면  null을 받는다

     /* 회원가입 */
     public boolean joinUser(User user) {
          userMapper.saveUser(user);

          return true;
     }

     /* 유저 탈퇴 */
     public void withdrawUser(String userId) {
          userMapper.withdrawUser(userId);
     }

}
