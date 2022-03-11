package com.hoin.boardStudy.board.controller;

import com.hoin.boardStudy.board.service.BoardService;
import com.hoin.boardStudy.board.service.ViewCountUpdater;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board") // controller의 부모에 해당되는 Mapping. prefix 역할
public class BoardController {
    
    private final BoardService Boardservice;
    private final ViewCountUpdater viewCountUpdater;

    // 전체 글 조회
    @GetMapping("/list.do")
    public String getBoardList(Model model) {
        model.addAttribute("list", Boardservice.getBoardList());
        return "board/list";
    }
    
    // 상세 페이지
    @GetMapping("/detail.do")
    public String getDetailPage(@RequestParam int boardId, Model model) {
        model.addAttribute("detail", Boardservice.getDetail(boardId));
        viewCountUpdater.increaseViewCount(boardId);
        return "board/detail";
    }


}
