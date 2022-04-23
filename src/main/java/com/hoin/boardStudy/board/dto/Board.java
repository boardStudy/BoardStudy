package com.hoin.boardStudy.board.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Board {
    private int boardId;
    private String writer;
    private String title;
    private String content;
    private int viewCount;
    private LocalDateTime regDate;
    private LocalDateTime updDate;
    private LocalDateTime delDate;
    private boolean newCheck;

    public Board(int boardId, String writer, String title, String content, LocalDateTime currentTime) {
        this.boardId = boardId;
        this.writer = writer;
        this.title = title;
        this.content = content;
        this.updDate = currentTime;
    }
}