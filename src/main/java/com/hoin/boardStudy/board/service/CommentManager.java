package com.hoin.boardStudy.board.service;

import com.hoin.boardStudy.board.mapper.CommentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentManager {

    private final CommentMapper commentMapper;

    @Transactional
    public int getCommentCount(int boardId) {
        return commentMapper.getCommentCount(boardId);
    }

}
