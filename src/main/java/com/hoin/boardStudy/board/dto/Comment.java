package com.hoin.boardStudy.board.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    private int commentId;
    private int boardId;
    private int depth;
    private String commenter;
    private String comment;
    private LocalDateTime regDate;
    private LocalDateTime updDate;

    public Comment(int boardId, String commenter, String comment, LocalDateTime currentTime){
        this.boardId = boardId;
        this.commenter = commenter;
        this.comment = comment;
        this.updDate = currentTime;
    }
}
