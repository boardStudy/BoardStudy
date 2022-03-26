package com.hoin.boardStudy.user.dto;

import lombok.*;

import java.time.LocalDateTime;


@Getter @Setter @ToString
@NoArgsConstructor // 파라메터가 없는 생성자를 생성한다. (기본 생성자)
@AllArgsConstructor // 클래스에 존재하는 모든 필드에 대한 생성자를 자동으로 생성한다.
public class User {

    private String userId;
    private String password;
    private String email;
    private LocalDateTime regDate;
    private LocalDateTime withdDate;
    private LocalDateTime updDate;
    private int withdStatus;
    private String name;
}

