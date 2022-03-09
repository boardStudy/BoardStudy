package com.hoin.boardStudy.board.service;

import com.hoin.boardStudy.board.mapper.BoardMapper;
import com.hoin.boardStudy.board.dto.Board;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class BoardService {

    private final BoardMapper boardMapper; // RequiredArgsConstructor 사용 (생성자 주입)

    // 읽기 전용 속성
    @Transactional(readOnly = true)
    public List<Board> getBoardList() {

        // 로그
        List<Board> result = boardMapper.getBoardList();
        log.info("결과 확인" + result.toString());

        return boardMapper.getBoardList();
    }

    // 상세 페이지
    @Transactional(readOnly = true)
    public Board getDetail(int boardId) {

        //로그
        Board result = boardMapper.getDetail(boardId);
        log.info("결과 확인" + result.toString());

        return boardMapper.getDetail(boardId);
    }
}
