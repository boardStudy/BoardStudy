package com.hoin.boardStudy.board.service;

import com.hoin.boardStudy.board.dto.Board;
import com.hoin.boardStudy.board.mapper.BoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class NewArticleChecker {

    private final BoardMapper boardMapper;

    // 로그인
    public boolean checkNewArticle(Board board, int expiryPeriod, String userId) {
        // 2일 이내, 읽지 않은 글 -> new 처리
        if(checkDate(board, expiryPeriod) && !checkRead(board, userId)) return true;
        return false;
    }

    // 비로그인
    public boolean checkNewArticle(Board board, int expiryPeriod) {
        // 2일 이내
        if(checkDate(board, expiryPeriod)) return true;
        return false;
    }

    // 날짜 확인
    private boolean checkDate(Board board, int expiryPeriod) {
        // 등록일 가져오기
        LocalDateTime regDate = board.getRegDate();
        // 2일 전이면 true , 이후면 false
        if(LocalDateTime.now().isBefore(regDate.plusDays(expiryPeriod))) return true;
        return false;
    }

    // 읽은 글 확인
    private boolean checkRead(Board board, String userId) {
        int boardId = board.getBoardId();
        return boardMapper.isRead(boardId, userId);
    }

}
