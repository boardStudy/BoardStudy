package com.hoin.boardStudy.user.service;

import com.hoin.boardStudy.user.dto.User;
import com.hoin.boardStudy.user.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class UserService {

     public final UserMapper userMapper;


     @Transactional
     public void joinUser(User user){
          userMapper.saveUser(user);
     }

     public User login(User user) {
          return userMapper.login(user);
     }

}
