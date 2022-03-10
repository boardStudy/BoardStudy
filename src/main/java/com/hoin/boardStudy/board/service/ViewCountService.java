package com.hoin.boardStudy.board.service;

import com.hoin.boardStudy.board.mapper.BoardMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ViewCountService {

    private final BoardMapper boardMapper;

    /**
     * 기능: 조회 수를 증가시킨다.
     * @param boardId
     */
    public void increaseViewCount(int boardId) {
        boardMapper.increaseViewCount(boardId);
    }
}
