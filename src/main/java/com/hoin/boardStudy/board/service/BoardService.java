package com.hoin.boardStudy.board.service;

import com.hoin.boardStudy.board.mapper.BoardMapper;
import com.hoin.boardStudy.board.dto.Board;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BoardService {

    @Autowired // spring에서 제공해주는 annotation. 향후 프레임워크를 바꿀 계획이 없으므로 익숙한 Autowired 우선 사용 -호인
    private BoardMapper boardMapper;

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
