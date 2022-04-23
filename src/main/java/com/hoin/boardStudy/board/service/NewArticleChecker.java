package com.hoin.boardStudy.board.service;

import com.hoin.boardStudy.board.mapper.BoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class NewArticleChecker {

    private final BoardMapper boardMapper;

    public boolean isNewArticle(int boardId) {
        // 등록일 가져오기
        LocalDateTime regDate = boardMapper.getDetail(boardId).getRegDate();
        // 2일 전이면 true , 이후면 false
        if(LocalDateTime.now().isBefore(regDate.plusDays(2))) return true;
        return false;

    }


}
