package com.hoin.boardstudy.board.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PageHandler {
    private int totalCount; // 게시물의 총 개수
    private int pageSize; // 한 페이지당 게시물 개수
    private int naviSize = 5; // 한 페이지 범위에 보여질 페이지의 개수
    private int page; // 현재 페이지
    private int totalPage; // 전체 페이지의 개수
    private int beginPage; // 시작 페이지
    private int endPage; // 마지막 페이지
    private boolean showPrev; // 이전 페이지 링크 포함 여부
    private boolean showNext; // 다음 페이지 링크 포함 여부

    public PageHandler(int totalCount, int page, int pageSize) {
        this.totalCount = totalCount;
        this.page = page;
        this.pageSize = pageSize;

        totalPage = (int)Math.ceil(totalCount / (double)pageSize);
        beginPage = page / (naviSize + 1) * naviSize + 1;
        endPage = Math.min(beginPage + naviSize - 1 , totalPage);
        showPrev = beginPage != 1;
        showNext = endPage != totalPage;
    }
}
