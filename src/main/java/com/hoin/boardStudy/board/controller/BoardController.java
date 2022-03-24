package com.hoin.boardStudy.board.controller;

import com.hoin.boardStudy.board.dto.Board;
import com.hoin.boardStudy.board.dto.BoardSaveRequest;
import com.hoin.boardStudy.board.dto.PageHandler;
import com.hoin.boardStudy.board.dto.PageInfo;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board") // controller의 부모에 해당되는 Mapping. prefix 역할
public class BoardController {

    private final BoardService boardService;
    private final ViewCountUpdater viewCountUpdater;

    // 전체 글 조회
    @GetMapping("list.do")
    public String getBoardList(Model model, PageInfo pageInfo) {

        // 등록된 글 총 개수
        int totalCount = boardService.getTotalCount();
        // 총개수, 현재 페이지, 보여줄 글 개수 map으로 전달
        Integer page = pageInfo.getPage();
        Integer pageSize = pageInfo.getPageSize();

        PageHandler pageHandler = new PageHandler(totalCount, page, pageSize);

        Map map = new HashMap();
        map.put("offset", (page-1) * pageSize);
        map.put("pageSize", pageSize);

        List<Board> list = boardService.getBoardList(map);

        // 게시판 정보, 페이징 정보 view단으로 전달
        model.addAttribute("list", list);
        model.addAttribute("pageHandler", pageHandler);
        return "board/list";
    }

    // 상세 페이지
    @GetMapping("detail.do")
    public String getDetailPage(@RequestParam int boardId, Model model) {
        model.addAttribute("detail", boardService.getDetail(boardId));
        viewCountUpdater.increaseViewCount(boardId);
        return "board/detail";
    }

    // 글쓰기 페이지
    @GetMapping("writeForm.do")
    public String getWriteForm(HttpServletRequest request) {

        HttpSession session = request.getSession();

        if (session == null) return "user/login";
        if (session.getAttribute("user") == null)  return "user/login";

        return "board/writeForm";
    }

    //글 수정 페이지
    @GetMapping("modify.do")
    public String modifyBoard(@RequestParam int boardId, Model model) {
        model.addAttribute("board", boardService.getDetail(boardId));
        return "board/modify";
    }

    // 글 저장 (등록, 수정)
    @PostMapping("saveBoard.do")
    public String saveBoard(BoardSaveRequest board, RedirectAttributes redirectAttributes, HttpSession session) {

        // 세션에서 로그인 ID를 가져와서 등록
        String writer = ((User) session.getAttribute("user")).getUserId();

        boardService.saveBoard(board, writer);
        redirectAttributes.addFlashAttribute("board", board);
        return "redirect:/board/list.do";
    }

    // 글 삭제
    @GetMapping("delete.do")
    public String deleteBoard(@RequestParam int boardId) {
        boardService.deleteBoard(boardId);

        return "redirect:/board/list.do";
    }
}
