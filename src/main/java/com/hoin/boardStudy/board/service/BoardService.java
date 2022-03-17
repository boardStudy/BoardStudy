package com.hoin.boardStudy.board.service;

import com.hoin.boardStudy.board.dto.BoardList;
import com.hoin.boardStudy.board.dto.BoardResult;
import com.hoin.boardStudy.board.dto.BoardSaveRequest;
import com.hoin.boardStudy.board.mapper.BoardMapper;
import com.hoin.boardStudy.board.dto.Board;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class BoardService {

    private final BoardMapper boardMapper; // RequiredArgsConstructor 사용 (생성자 주입)

    /**
     * 게시판 목록 조회
     * 기능 : 게시판의 모든 글들을 불러온다.
     * @return
     */
    @Transactional(readOnly = true)
    public List<BoardList> getBoardList() {
        return boardMapper.getBoardList();
    }

    /**
     * 글 상세 페이지
     * 기능 : 사용자가 선택한 글 상세 페이지를 불러온다.
     * @param boardId
     * @return
     */
    @Transactional(readOnly = true)
    public BoardResult getDetail(int boardId) {
        BoardResult result = boardMapper.getDetail(boardId);
        log.info(result.toString());
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
                new Board(board.getBoardId(),
                        writer,
                        board.getTitle(),
                        board.getContent(),
                        board.getCurrentTime());
        boardMapper.saveBoard(saveBoard);
    }

    @Transactional
    public void deleteBoard(int boardId) {
        boardMapper.deleteBoard(boardId);
    }
}
