package com.hoin.boardStudy.user.mapper;

import com.hoin.boardStudy.user.dto.User;
import org.apache.ibatis.annotations.Mapper;


@Mapper

public interface UserMapper {

    void saveUser(User user);

    User login(User user) throws Exception;

}

// mapper.xml 써둔 쿼리문이랑 위에 써둔 매칭되야한다.