package com.hoin.boardStudy.user.service;

import com.hoin.boardStudy.user.dto.User;
import com.hoin.boardStudy.user.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

     public final UserMapper userMapper;


     BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

     @Transactional
     public void joinUser(User user){

          user.setPassword(passwordEncoder.encode(user.getPassword())); // 암호화처리
          userMapper.saveUser(user);
     }

     public User login(User user) throws Exception {
          return userMapper.login(user);
     }

     @Transactional
     public void joinUser(User user){
          BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
          user.setPassword(passwordEncoder.encode(user.getPassword())); // 암호화처리
          userMapper.saveUser(user);
     }


}
