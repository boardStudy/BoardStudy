package com.hoin.boardStudy.board.mapper;

import com.hoin.boardStudy.board.dto.Board;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {

    // 글 전체 목록 조회
    List<Board> getBoardList();

}
