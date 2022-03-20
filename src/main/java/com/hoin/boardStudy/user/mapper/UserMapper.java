package com.hoin.boardStudy.user.mapper;
import com.hoin.boardStudy.user.dto.User;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface UserMapper {

    /* 유저정보 체크 */
    User userCheck(String userId);

    /* 회원가입 */
    void saveUser(User user);

    /* 탈퇴여부 확인 */
    void withdrawUser(String userId);

}
