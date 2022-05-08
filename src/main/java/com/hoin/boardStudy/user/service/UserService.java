package com.hoin.boardStudy.user.service;
import com.hoin.boardStudy.user.dto.User;
import com.hoin.boardStudy.user.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;

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

     // 회원가입 유효성 검사
     public Map<String, String> validateHandling(Errors errors) {
          Map<String, String> validatorResult = new HashMap<>();

          for (FieldError error : errors.getFieldErrors()) {
               String validKeyName = String.format("valid_%s", error.getField());
               validatorResult.put(validKeyName, error.getDefaultMessage());
          }
          return validatorResult;
     }

     // 아이디 중복 체크
     public int userIdCheck(String userId) {
          int cnt = userMapper.userIdCheck(userId);
          return cnt;
     }

     // 이메일 중복 체크
     public int phoneCheck(String phone) {
          int cnt = userMapper.phoneCheck(phone);
          return cnt;
     }


     /* 유저 탈퇴 */
     public void withdrawUser(String userId) {
          userMapper.withdrawUser(userId);
     }

}
