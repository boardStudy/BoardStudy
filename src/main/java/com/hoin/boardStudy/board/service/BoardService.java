package com.hoin.boardStudy.board.service;

import com.hoin.boardStudy.board.dto.BoardSaveRequest;
import com.hoin.boardStudy.board.mapper.BoardMapper;
import com.hoin.boardStudy.board.dto.Board;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class BoardService {

    private final BoardMapper boardMapper; // RequiredArgsConstructor 사용 (생성자 주입)

    /**
     * 게시판 목록 조회
     * 기능 : 게시판의 모든 글들을 불러온다.
     * @return
     * @param map
     */
    @Transactional(readOnly = true)
    public List<Board> getBoardList(Map map) {

        return boardMapper.getBoardList(map);
    }

    public int getTotalCount() {
        return boardMapper.getTotalCount();
    }

    /**
     * 글 상세 페이지
     * 기능 : 사용자가 선택한 글 상세 페이지를 불러온다.
     * @param boardId
     * @return
     */
    @Transactional(readOnly = true)
    public Board getDetail(int boardId) {

        return boardMapper.getDetail(boardId);
    }

    /**
     * 글 저장하기
     * 기능 : 사용자가 글을 등록, 수정한다.
     * @param board
     */
    @Transactional
    public void saveBoard(BoardSaveRequest board, String writer) {
        Board saveBoard =
                new Board(
                        board.getBoardId(),
                        writer,
                        board.getTitle(),
                        board.getContent(),
                        LocalDateTime.now()
                );
        boardMapper.saveBoard(saveBoard);
    }

    /**
     * 글 삭제하기
     * 기능 : 등록된 글을 삭제한다.
     * @param boardId
     */
    @Transactional
    public void deleteBoard(int boardId) {
        boardMapper.deleteBoard(boardId);
    }
}
