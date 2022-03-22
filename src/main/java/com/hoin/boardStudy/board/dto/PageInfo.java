package com.hoin.boardStudy.board.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PageInfo {
    private int page;
    private int pageSize;
}
