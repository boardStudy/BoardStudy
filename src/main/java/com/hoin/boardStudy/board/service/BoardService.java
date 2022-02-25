package com.hoin.boardStudy.board.service;

import com.hoin.boardStudy.board.dao.BoardDAO;
import com.hoin.boardStudy.board.vo.Board;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class BoardService {

    @Autowired // spring에서 제공해주는 annotation. 향후 프레임워크를 바꿀 계획이 없으므로 익숙한 Autowired 우선 사용 -호인
    private BoardDAO dao;

    public List<Board> getBoardList() {
        return dao.getBoardList();
    }
}
