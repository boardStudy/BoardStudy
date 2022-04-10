package com.hoin.boardStudy.board.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FileInfo {
    private int fileId;
    private int boardId;
    private String originalName;
    private String saveName;
    private long size;
    private String extension;
    private LocalDateTime regDate = LocalDateTime.now();
}
