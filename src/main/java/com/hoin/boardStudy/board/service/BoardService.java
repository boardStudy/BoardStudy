package com.hoin.boardStudy.board.service;

import com.hoin.boardStudy.board.dto.BoardSaveRequest;
import com.hoin.boardStudy.board.dto.PrevAndNext;
import com.hoin.boardStudy.board.mapper.BoardMapper;
import com.hoin.boardStudy.board.dto.Board;
import com.hoin.boardStudy.user.dto.User;
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

    private static final String NO_PREV = "이전 글이 없습니다.";
    private static final String NO_NEXT = "다음 글이없습니다.";

    private final BoardMapper boardMapper;
    private final DateChecker DateChecker;
    private final CommentManager commentManager;

    /**
     * 게시판 목록 조회
     * 기능 : 게시판의 모든 글들을 불러온다.
     * @return
     * @param map
     */
    @Transactional(readOnly = true)
    public List<Board> getBoardList(Map map) {
        // 리스트
        List<Board> list = boardMapper.getBoardList(map);

        for(Board board : list) {
            int boardId = board.getBoardId();
            // 새 글 확인
            checkNewArticle(board, boardId,2);
            // 댓글 개수 확인
            checkCommentCount(board, boardId);
        }

        return list;
    }

    // 공지사항
    @Transactional(readOnly = true)
    public List<Board> getNewNoticeList() {
        List<Board> list = boardMapper.getNewNoticeList();
        return list;
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
                        board.getType(),
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
    
    // 작성자 정보
    @Transactional
    public User getWriter(int boardId) {
        return boardMapper.getWriter(boardId);
    }

    // 페이지 이동
    @Transactional
    public PrevAndNext getPageToMove(int boardId) {

        int prev = getPrevBoardId(boardId);
        int next = getNextBoardId(boardId);
        String prevTitle = getPrevTitle(boardId);
        String nextTitle = getNextTitle(boardId);

        PrevAndNext prevAndNext = new PrevAndNext(prev, next, prevTitle, nextTitle);

        return prevAndNext;
    }
    
    // 이전 글 게시물 번호
    private int getPrevBoardId(int boardId) {
        if(boardMapper.getPrevPage(boardId) != null) return boardMapper.getPrevPage(boardId).getBoardId();
        return 0;
    }

    // 이전 글 게시물 제목
    private String getPrevTitle(int boardId) {
        if(boardMapper.getPrevPage(boardId) != null) return boardMapper.getPrevPage(boardId).getTitle();
        return NO_PREV;
    }
    
    // 다음 글 게시물 번호
    private int getNextBoardId(int boardId) {
        if(boardMapper.getNextPage(boardId) != null) return boardMapper.getNextPage(boardId).getBoardId();
        return 0;
    }
    
    // 다음 글 게시판 제목
    private String getNextTitle(int boardId) {
        if(boardMapper.getNextPage(boardId) != null) return boardMapper.getNextPage(boardId).getTitle();
        return NO_NEXT;
    }

    // 날짜 체크 (General)
    private void checkNewArticle(Board board, int boardId, int expiryPeriod) {
        board.setExpirationOrNot(DateChecker.isNewArticle(boardId, expiryPeriod));
    }

    // 댓글 개수
    private void checkCommentCount(Board board, int boardId) {
        board.setCommentCount(commentManager.getCommentCount(boardId));
    }

}