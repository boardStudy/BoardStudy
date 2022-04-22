package com.hoin.boardStudy.user.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class Email {
    private String senderName;      // 발신자 이름
    private String senderMail;      // 발신자 이메일 주소
    private String email;       // 수진자(유저) 이메일 주소
    private String subject;         // 제목
    private String content;         // 본문(내용)
}