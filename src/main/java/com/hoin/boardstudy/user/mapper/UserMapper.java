package com.hoin.boardstudy.user.mapper;
import com.hoin.boardstudy.user.dto.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.transaction.annotation.Transactional;


@Mapper
public interface UserMapper {

    /* 유저정보 조회 */
    @Transactional
    User getUserInfo(String userId);

    /* 유저정보 수정 */
    public void modifyUserInfo(User user);

    /* 회원가입 */
    void saveUser(User user);

    /* 탈퇴여부 확인 */
    void withdrawUser(String userId);

}
