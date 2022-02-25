package com.hoin.boardStudy.board.dao;

import com.hoin.boardStudy.board.vo.Board;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardDAO {

    // 글 전체 목록 조회
    public List<Board> getBoardList();

}
