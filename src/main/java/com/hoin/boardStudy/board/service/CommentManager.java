package com.hoin.boardStudy.board.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hoin.boardStudy.board.client.AlarmClient;
import com.hoin.boardStudy.board.dto.AlarmRequest;
import com.hoin.boardStudy.board.dto.Comment;
import com.hoin.boardStudy.board.dto.ModifyRequest;
import com.hoin.boardStudy.board.mapper.BoardMapper;
import com.hoin.boardStudy.board.mapper.CommentMapper;
import com.hoin.boardStudy.user.dto.DomainProperties;
import com.hoin.boardStudy.user.dto.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentManager {

    private final CommentMapper commentMapper;
    private final BoardMapper boardMapper;
    private final AlarmClient alarmClient;



    // 댓글 개수
    @Transactional
    public int getCommentCount(int boardId) {
        return commentMapper.getCommentCount(boardId);
    }

    // 댓글 목록
    @Transactional
    public List<Comment> getCommentList(int boardId) {
        return commentMapper.getCommentList(boardId);
    }

    // 댓글 입력
    @Transactional
    public void insertComment(Comment comment, String commenter){
        // 댓글 저장
        Comment commentRequest = new Comment(
                comment.getBoardId(),
                commenter,
                comment.getParentId(),
                comment.getComment(),
                LocalDateTime.now()
        );
        commentMapper.insertComment(commentRequest);

    }

    // 알림 서비스 API 호출
    @Transactional
    public void alarmByEmail(Comment comment) {

        User articleWriter = boardMapper.getWriter(comment.getBoardId());

        AlarmRequest alarmRequest = new AlarmRequest(
                articleWriter.getEmail(),
                articleWriter.getUserId(),
                comment.getCommenter(),
                comment.getComment()
        );

        alarmClient.alarmByEmail(alarmRequest);
    }

    // 댓글 수정
    @Transactional
    public void modifyComment(ModifyRequest modifyRequest) {
        ModifyRequest request = new ModifyRequest(
                modifyRequest.getCommentId(),
                modifyRequest.getCommenter(),
                modifyRequest.getComment(),
                LocalDateTime.now()
        );
        commentMapper.modifyComment(request);
    }

    // 댓글 삭제
    @Transactional
    public void deleteComment(Comment comment){
        int commentId = comment.getCommentId();
        int boardId = comment.getBoardId();
        Integer parentId = comment.getParentId();
        commentMapper.deleteComment(commentId, boardId, parentId);
    }
}
