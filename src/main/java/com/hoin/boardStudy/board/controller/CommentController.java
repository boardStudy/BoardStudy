package com.hoin.boardStudy.board.controller;

import com.hoin.boardStudy.board.dto.Comment;
import com.hoin.boardStudy.board.dto.ModifyRequest;
import com.hoin.boardStudy.board.service.CommentManager;
import com.hoin.boardStudy.user.dto.User;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {

    private final CommentManager commentManager;

    // 댓글 목록
    @GetMapping("list.do")
    public List<Comment> getCommentList(@RequestParam int boardId) {
        List<Comment> commentList = commentManager.getCommentList(boardId);
        return commentList;
    }

    // 댓글 입력
    @PostMapping("insert.do")
    public void insertComment(@RequestBody Comment comment, HttpSession session) {
        String writer = ((User) session.getAttribute("user")).getUserId();

        comment.setCommenter(writer);
        commentManager.insertComment(comment, writer);
    }

    // 댓글 수정
    @PostMapping("modify.do")
    public void modifyComment(@RequestBody ModifyRequest modifyRequest) {
        commentManager.modifyComment(modifyRequest);
    }

    // 댓글 삭제
    @PostMapping("/delete.do")
    public void deleteComment(@RequestBody Comment comment){

        commentManager.deleteComment(comment);
    }

}
