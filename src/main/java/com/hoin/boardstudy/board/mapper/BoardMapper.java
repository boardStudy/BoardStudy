package com.hoin.boardstudy.board.mapper;

import com.hoin.boardstudy.board.dto.Board;
import com.hoin.boardstudy.board.dto.FileInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface BoardMapper {

    // 글 전체 목록 조회
    List<Board> getBoardList(Map map);

    // 전체 글 개수 조회
    int getTotalCount();

    // 상세 페이지
    Board getDetail(int boardId);

    // 조회수 기능
    void increaseViewCount(int boardId);

    // 글 저장 기능 (등록, 수정)
    void saveBoard(Board board);

    // 파일 업로드
    void saveFile(FileInfo fileInfo);

    void modifyFile(FileInfo fileInfo);

    // 글 삭제 기능
    void deleteBoard(int boardId);

    // 파일 삭제
    void deleteFile(int boardId);

    // 파일 정보 가져오기
    FileInfo getFileInfo(int boardId);

}
