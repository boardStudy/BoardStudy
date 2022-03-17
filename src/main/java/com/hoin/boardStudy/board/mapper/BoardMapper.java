package com.hoin.boardStudy.board.mapper;

import com.hoin.boardStudy.board.dto.Board;
import com.hoin.boardStudy.board.dto.BoardList;
import com.hoin.boardStudy.board.dto.BoardResult;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {

    // 글 전체 목록 조회
    List<BoardList> getBoardList();

    // 상세 페이지
    BoardResult getDetail(int boardId);

    // 조회수 기능
    void increaseViewCount(int boardId);

    // 글 저장 기능 (등록, 수정)
    void saveBoard(Board board);

    // 글 삭제 기능
    void deleteBoard(int boardId);

}
