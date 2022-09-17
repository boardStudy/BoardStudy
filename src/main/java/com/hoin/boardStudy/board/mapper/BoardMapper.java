package com.hoin.boardStudy.board.mapper;

import com.hoin.boardStudy.board.dto.Board;
import com.hoin.boardStudy.board.dto.FileInfo;
import com.hoin.boardStudy.board.dto.PrevAndNext;
import com.hoin.boardStudy.user.dto.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface BoardMapper {

    // 글 전체 목록 조회
    List<Board> getBoardList(Map map);

    // 최신 공지사항
    List<Board> getNewNoticeList();

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
    void deleteFile(int fileId);

    // 글에 등록된 파일 리스트 가져오기
    List<FileInfo> getFiles(int boardId);

    // 파일 정보 가져오기
    FileInfo getFileInfo(int fileId);

    // 작성자 정보 가져오기
    User getWriter(int boardId);

    // 이전 페이지
    Board getPrevPage(int boardId);

    // 다음 페이지
    Board getNextPage(int boardId);
}
