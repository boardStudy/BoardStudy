package com.hoin.boardStudy.user.mapper;
import com.hoin.boardStudy.user.dto.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.transaction.annotation.Transactional;


@Mapper
public interface UserMapper {

    /* 유저정보 조회 */
    @Transactional
    User getUserInfo(String userId);

    /* 유저정보 수정 */
    void modifyUserInfo(User user);

    /* 회원가입 */
    void saveUser(User user);

    // 아이디 중복 검사
    int userIdCheck(String userId);

    // 전화번호 중복 검사
    int phoneCheck(String phone);

    /* 탈퇴여부 확인 */
    void withdrawUser(String userId);

}
