package com.hoin.boardStudy.board.controller;

import com.hoin.boardStudy.board.dto.Comment;
import com.hoin.boardStudy.board.dto.ModifyRequest;
import com.hoin.boardStudy.board.service.BoardService;
import com.hoin.boardStudy.board.service.CommentManager;
import com.hoin.boardStudy.user.dto.User;
import com.hoin.boardStudy.user.service.EmailManagement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comments")
public class CommentController {

    private final CommentManager commentManager;
    private final EmailManagement emailManagement;
    private final BoardService boardService;

    // 댓글 목록
    @GetMapping("/{boardId}")
    public ResponseEntity<List<Comment>> getCommentList(@PathVariable("boardId") int boardId) {
        List<Comment> commentList = commentManager.getCommentList(boardId);
        return ResponseEntity.status(HttpStatus.OK).body(commentList);
    }

    // 댓글 입력
    @PostMapping
    public void insertComment(@RequestBody Comment comment, HttpSession session) {
        String commenter = ((User) session.getAttribute("user")).getUserId();

        int boardId = comment.getBoardId();
        User articleWriter = boardService.getWriter(boardId);
        String to = articleWriter.getEmail();

        comment.setCommenter(commenter);
        commentManager.insertComment(comment, commenter);
        emailManagement.sendMail(to,comment);
    }


    // 댓글 수정
    @PatchMapping
    public void modifyComment(@RequestBody ModifyRequest modifyRequest) {

        commentManager.modifyComment(modifyRequest);

    }

    // 댓글 삭제
    @DeleteMapping
    public void deleteComment(@RequestBody Comment comment){

        commentManager.deleteComment(comment);
    }

}
