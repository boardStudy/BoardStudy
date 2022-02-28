package com.hoin.boardStudy.board.service;

import com.hoin.boardStudy.board.mapper.BoardMapper;
import com.hoin.boardStudy.board.dto.Board;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardMapper boardMapper; // RequiredArgsConstructor 사용 (생성자 주입)

    private final Logger LOGGER = LoggerFactory.getLogger(BoardService.class);

    // 읽기 전용 속성
    @Transactional(readOnly = true)
    public List<Board> getBoardList() {

        // 로그
        List<Board> result = boardMapper.getBoardList();
        LOGGER.info("결과 확인" + result.toString());

        return boardMapper.getBoardList();
    }
}
