package com.hoin.boardStudy.board.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ModifyRequest {
    private int commentId;
    private String commenter;
    private String comment;
    private LocalDateTime updDate = LocalDateTime.now();
}
