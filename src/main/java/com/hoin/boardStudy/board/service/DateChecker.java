package com.hoin.boardStudy.board.service;

import com.hoin.boardStudy.board.mapper.BoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class DateChecker {

    private final BoardMapper boardMapper;

    public boolean isNewArticle(int boardId, int expiryPeriod) {
        // 등록일 가져오기
        LocalDateTime regDate = boardMapper.getDetail(boardId).getRegDate();
        // 2일 전이면 true , 이후면 false
        if(LocalDateTime.now().isBefore(regDate.plusDays(expiryPeriod))) return true;
        return false;

    }

    public boolean checkDate(int boardId, int expiryPeriod) {
        LocalDateTime regDate = boardMapper.getDetail(boardId).getRegDate();
        if(LocalDateTime.now().isBefore(regDate.plusDays(expiryPeriod))) return true;
        return false;
    }


}
