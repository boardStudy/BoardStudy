package com.hoin.boardStudy.board.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommentMapper {

    // 댓글 개수
    int getCommentCount(int boardId);
}
