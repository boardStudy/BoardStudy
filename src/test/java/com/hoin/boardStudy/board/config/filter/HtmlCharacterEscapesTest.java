package com.hoin.boardStudy.board.config.filter;

import com.fasterxml.jackson.core.SerializableString;
import com.hoin.boardStudy.board.controller.CommentController;
import com.hoin.boardStudy.board.dto.Comment;
import com.hoin.boardStudy.board.service.CommentManager;
import org.cef.handler.CefLoadHandler;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(CommentController.class)
@ActiveProfiles("local")
class HtmlCharacterEscapesTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CommentManager commentManager;

    @DisplayName("XSS 통합 테스트")
    @Test
    void xssTest() throws Exception{
        //given

        //List<Comment> commentList = commentManager.getCommentList(boardId);
        Comment testComment =
                new Comment(1, null, null, "&lt;li&gt;content&lt;/li&gt", null);
        given(commentManager.getCommentList(any()))
                .willReturn(List.of(testComment));

        //when & then
        mockMvc.perform(get("/comments/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").isArray())
                .andExpect(jsonPath("$.data[0].name").value("user1"))
                .andExpect(jsonPath("$.data[0].title").value("title1"));
    }

    
    
}


