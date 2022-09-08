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

    private final BoardMapper boardMapper;
    private final NewArticleChecker newArticleChecker;
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
        // new 유무 확인 , 댓글 개수 확인 --> 후에 메소드 분리 필요
        for(int i = 0; i < list.size(); i ++) {
            int boardId = list.get(i).getBoardId();
            list.get(i).setNewCheck(newArticleChecker.isNewArticle(boardId));
            list.get(i).setCommentCount(commentManager.getCommentCount(boardId));
        }
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

        int prev = 0;
        int next = 0;
        String prevTitle = "이전 글이 없습니다.";
        String nextTitle = "다음 글이 없습니다.";

        // 이전 글이 존재할 경우
        if(boardMapper.getPrevPage(boardId) != null) {
            prev = boardMapper.getPrevPage(boardId).getBoardId();
            prevTitle = boardMapper.getPrevPage(boardId).getTitle();
        }

        // 다음 글이 존재할 경우
        if(boardMapper.getNextPage(boardId) != null) {
            next = boardMapper.getNextPage(boardId).getBoardId();
            nextTitle = boardMapper.getNextPage(boardId).getTitle();
        }

        PrevAndNext prevAndNext = new PrevAndNext(prev, next, prevTitle, nextTitle);

        return prevAndNext;
    }
}
