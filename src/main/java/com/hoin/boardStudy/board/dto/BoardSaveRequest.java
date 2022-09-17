package com.hoin.boardStudy.board.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BoardSaveRequest implements Serializable {
    private int boardId; // 글 번호
    private String writer; // 작성자
    private String title; // 제목
    private String content; // 내용
    private String notice; // 공지사항
    private LocalDateTime currentTime = LocalDateTime.now(); // 현재시간
}
