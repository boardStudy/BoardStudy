package com.hoin.boardStudy.board.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PrevAndNext {
    private int prev;
    private int next;
    private String prevTitle;
    private String nextTitle;
}
