package com.hoin.boardStudy.board.mapper;

import com.hoin.boardStudy.board.dto.Comment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentMapper {

    // 댓글 개수
    int getCommentCount(int boardId);

    // 댓글 가져오기
    List<Comment> getCommentList(int boardId);

    // 댓글 작성
    void insertComment(Comment comment);

    // 댓글 수정
    void modifyComment(Comment comment);

    // 댓글 삭제
    void deleteComment(int CommentId);
    
}
