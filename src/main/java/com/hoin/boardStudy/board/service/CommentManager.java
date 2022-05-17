package com.hoin.boardStudy.board.service;

import com.hoin.boardStudy.board.dto.Comment;
import com.hoin.boardStudy.board.dto.ModifyRequest;
import com.hoin.boardStudy.board.mapper.CommentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentManager {

    private final CommentMapper commentMapper;

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
        Comment c = new Comment(
                comment.getBoardId(),
                commenter,
                comment.getParentId(),
                comment.getComment(),
                LocalDateTime.now()
        );
        commentMapper.insertComment(c);

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