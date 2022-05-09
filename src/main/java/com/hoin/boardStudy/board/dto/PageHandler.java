package com.hoin.boardStudy.board.dto;

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

        // pageSize Min, Max 값 제한
        if(pageSize > 20 || pageSize < 0) pageSize = 5;

        // totalPage 먼저 구하기
        totalPage = (int)Math.ceil(totalCount / (double)pageSize);

        // 게시물 0개 처리
        if(totalCount == 0) totalPage = 1;

        // page Min값을 첫 페이지로 설정
        if(page <= 0) page = 1;

        // page Max값을 마지막 페이지로 설정
        if(page > totalPage) page = totalPage;

        this.totalCount = totalCount;
        this.page = page;
        this.pageSize = pageSize;

        beginPage = (((int)Math.ceil(page / (double)naviSize))-1) * naviSize + 1;
        endPage = Math.min(beginPage + naviSize - 1 , totalPage);
        showPrev = beginPage != 1;
        showNext = endPage != totalPage;



    }
}
