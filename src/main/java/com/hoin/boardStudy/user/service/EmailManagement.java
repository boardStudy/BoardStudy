package com.hoin.boardStudy.user.service;

import com.hoin.boardStudy.board.dto.Comment;
import com.hoin.boardStudy.board.service.BoardService;
import com.hoin.boardStudy.user.dto.DomainProperties;
import com.hoin.boardStudy.user.dto.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;

import static com.hoin.boardStudy.user.dto.EmailProperties.*;

@Service
@RequiredArgsConstructor
public class EmailManagement { // 이메일 재사용성을 위해 분리

    private final JavaMailSender javaMailSender;
    private final DomainProperties domainProperties;
    private final BoardService boardService;

    public void sendMail(String to,String key) {

        try {
            MimeMessage mail = javaMailSender.createMimeMessage();
            MimeMessageHelper mailHelper = new MimeMessageHelper(mail, true, "UTF-8");
            mailHelper.setFrom(FROM);
            mailHelper.setTo(to);
            mailHelper.setSubject(TITLE);
            mailHelper.setText(String.format(CONTENT,domainProperties.getName(),to,key), true);
            javaMailSender.send(mail);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendMail(Comment comment) {

        String commenter = comment.getCommenter();
        String title = commenter + "님이 댓글을 남겼습니다.";

        int boardId = comment.getBoardId();
        User articleWriter = boardService.getWriter(boardId);
        String to = articleWriter.getEmail();
        String writer = articleWriter.getUserId();

        if(!commenter.equals(writer)) {
            try {
                MimeMessage mail = javaMailSender.createMimeMessage();
                MimeMessageHelper mailHelper = new MimeMessageHelper(mail, "UTF-8");
                mailHelper.setFrom(FROM);
                mailHelper.setTo(to);
                mailHelper.setSubject(title);
                mailHelper.setText(commenter + "님이 " + comment.getComment() + "라고 댓글을 남겼습니다.");
                javaMailSender.send(mail);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}

