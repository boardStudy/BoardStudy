package com.hoin.boardStudy.board.controller;

import com.hoin.boardStudy.board.dto.Board;
import com.hoin.boardStudy.board.service.BoardService;
import com.hoin.boardStudy.board.service.ViewCountUpdater;
import com.hoin.boardStudy.user.dto.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board") // controller의 부모에 해당되는 Mapping. prefix 역할
public class BoardController {

    private final BoardService boardService;
    private final ViewCountUpdater viewCountUpdater;

    // 전체 글 조회
    @GetMapping("/list.do")
    public String getBoardList(Model model) {
        model.addAttribute("list", boardService.getBoardList());
        return "board/list";
    }

    // 상세 페이지
    @GetMapping("/detail.do")
    public String getDetailPage(@RequestParam int boardId, Model model) {
        model.addAttribute("detail", boardService.getDetail(boardId));
        viewCountUpdater.increaseViewCount(boardId);
        return "board/detail";
    }

    // 글쓰기 페이지
    @GetMapping("/writeForm.do")
    public String getWriteForm(HttpServletRequest request) {

        // 로그인 체크 따로 메서드 만들어서 사용하면 좋을 듯
        HttpSession session = request.getSession();
        if (session != null && session.getAttribute("User") != null) {
            return "board/writeForm";
        }else {
            return "user/login";
        }
    }

    // 글 저장
    @PostMapping("/saveBoard.do")
    public String saveBoard(@ModelAttribute Board board, RedirectAttributes redirectAttributes, HttpSession session) {

        // 세션에서 로그인 ID를 가져와서 등록
        String writer = ((User) session.getAttribute("User")).getUserId();

        boardService.insertBoard(board,writer);
        redirectAttributes.addFlashAttribute("board", board);
        return "redirect:/board/list.do";
    }
}
