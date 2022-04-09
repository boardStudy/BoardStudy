package com.hoin.boardstudy.user.service;
import com.hoin.boardstudy.user.dto.User;
import com.hoin.boardstudy.user.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

     public final UserMapper userMapper;
     BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
     private final PasswordManagement passwordManagement;

     /* 유저정보 조회 */
     public User getUserInfo(String userId) {

          return userMapper.getUserInfo(userId);
     }
     @Transactional
     /* 유저정보 수정 */
     public void modifyUserInfo(User user) {
          userMapper.modifyUserInfo(user);
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
