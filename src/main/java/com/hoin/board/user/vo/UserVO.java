package com.hoin.board.user.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter@Setter@ToString
public class UserVO {
    private String userID;
    private String password;
    private String email;
    private LocalDateTime regDate;
    private LocalDateTime witDate;
    private LocalDateTime updDate;
}
