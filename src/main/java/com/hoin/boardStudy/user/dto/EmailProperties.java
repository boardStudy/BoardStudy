package com.hoin.boardStudy.user.dto;

public class EmailProperties {

    public static final String FROM = "dahoonemailtest@gmail.com";
    public static final String TITLE = "안녕하세요. BoardStudy 인증 메일입니다.";
    public static final String CONTENT =
            "<h1>메일인증</h1>" +
            "<br/>BoardStudy 가입해주셔서 진심으로 감사드립니다." +
            "<br/>[이메일 인증하기]을 클릭하여 계정을 활성화 해주세요!"+
            "<br/><a href='http://%s/user/registerEmail?email=%s&key=%s'>이메일 인증하기</a>" +
            "<br/>감사합니다 :)";
}
