package com.hoin.boardStudy.board.controller;

import com.hoin.boardStudy.board.dto.Board;
import com.hoin.boardStudy.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/board") // controller의 부모에 해당되는 Mapping. prefix 역할
public class BoardController {

    // 기존 필드 주입에서 생성자 주입 방식으로 변경 (22.02.27)
    private final BoardService service;

    public BoardController(BoardService service) {
        this.service = service;
    }

    /**
     * 게시판 목록 조회
     * 기능 : 게시판에 모든 글들을 불러온다.
     * @param model
     * @return
     */
    @GetMapping("/list.do")
    public String getBoardList(Model model) {

        model.addAttribute("list", service.getBoardList());

        return "list";
    }


}
