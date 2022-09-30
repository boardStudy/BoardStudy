package com.hoin.boardStudy.board.service;

import com.hoin.boardStudy.board.mapper.BoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class NewArticleChecker {

    private final BoardMapper boardMapper;

    // 로그인
    public boolean checkNewArticle(int boardId, int expiryPeriod, String userId) {
        // 2일 이내, 읽지 않은 글 -> new 처리
        if(checkDate(boardId, expiryPeriod) && !checkRead(boardId, userId)) return true;
        return false;
    }

    // 비로그인
    public boolean checkNewArticle(int boardId, int expiryPeriod) {
        // 2일 이내
        if(checkDate(boardId, expiryPeriod)) return true;
        return false;
    }

    // 날짜 확인
    private boolean checkDate(int boardId, int expiryPeriod) {
        // 등록일 가져오기
        LocalDateTime regDate = boardMapper.getDetail(boardId).getRegDate();
        // 2일 전이면 true , 이후면 false
        if(LocalDateTime.now().isBefore(regDate.plusDays(expiryPeriod))) return true;
        return false;
    }

    // 읽은 글 확인
    private boolean checkRead(int boardId, String userId) {
        return boardMapper.isRead(boardId, userId);
    }

}
