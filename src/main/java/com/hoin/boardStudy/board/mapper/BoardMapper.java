package com.hoin.boardStudy.board.mapper;

import com.hoin.boardStudy.board.dto.Board;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {

    // 글 전체 목록 조회
    List<Board> getBoardList();

    // 상세 페이지
   Board getDetail(int boardId);

    // 조회수 기능
    void increaseViewCount(int boardId);

}
