package com.hoin.boardStudy.board.mapper;

import com.hoin.boardStudy.board.dto.Board;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface BoardMapper {

    // 글 전체 목록 조회
    @Select("SELECT * FROM TBL_BOARD")
    List<Board> getBoardList();

}
