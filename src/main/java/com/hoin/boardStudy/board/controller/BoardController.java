package com.hoin.boardStudy.board.controller;

import com.hoin.boardStudy.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board") // controller의 부모에 해당되는 Mapping. prefix 역할
public class BoardController {
    
    private final BoardService service; // requiredArgsConstructor 사용

    /**
     * 게시판 목록 조회
     * 기능 : 게시판에 모든 글들을 불러온다.
     * @param model
     * @return
     */
    @GetMapping("/list.do")
    public String getBoardList(Model model) {

        model.addAttribute("list", service.getBoardList());
        return "board/list";
    }


}
