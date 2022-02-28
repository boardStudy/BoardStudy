package com.hoin.boardStudy.board.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class Board {
    private int boardId;
    private String writer;
    private String title;
    private String content;
    private int views;
    private LocalDateTime regDate;
    private LocalDateTime updDate;
    private LocalDateTime delDate;
}