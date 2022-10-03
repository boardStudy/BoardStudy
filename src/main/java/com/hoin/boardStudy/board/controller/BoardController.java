package com.hoin.boardStudy.board.controller;

import com.hoin.boardStudy.board.dto.*;
import com.hoin.boardStudy.board.mapper.CommentMapper;
import com.hoin.boardStudy.board.service.BoardService;
import com.hoin.boardStudy.board.service.FileManager;
import com.hoin.boardStudy.board.service.ViewCountUpdater;
import com.hoin.boardStudy.user.dto.User;
import com.hoin.boardStudy.user.service.EmailManagement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
@Slf4j
public class BoardController {

    private final BoardService boardService;
    private final FileManager fileManager;
    private final ViewCountUpdater viewCountUpdater;
    private final EmailManagement emailManagement;

    // 전체 글 조회
    @GetMapping("list.do")
    public String getBoardList(Model model, PageInfo requestedPageInfo, HttpSession session) {

        // 글 목록
        List<Board> list = null;
        PageHandler processedPageInfo = getProcessedPageInfo(requestedPageInfo);
        if(session.getAttribute("user") != null) list = getListWhenLoggedIn(session, pagination(processedPageInfo));
        if(session.getAttribute("user") == null) list = getListWhenNotLoggedIn(pagination(processedPageInfo));

        // 공지사항
        List<Board> notice = boardService.getNewNoticeList();

        model.addAttribute("list", list);
        model.addAttribute("notice", notice);
        model.addAttribute("pageHandler", processedPageInfo);

        return "board/list";
    }

    // 상세 페이지
    @GetMapping("detail.do")
    public String getDetailPage(@RequestParam int boardId, Model model, HttpSession session) {

        if(session.getAttribute("user") != null) model.addAttribute("detail", boardService.getDetail(boardId, getLoginUserId(session)));
        if(session.getAttribute("user") == null) model.addAttribute("detail", boardService.getDetail(boardId));

        model.addAttribute("fileInfo", fileManager.getFiles(boardId));
        model.addAttribute("move", boardService.getPageToMove(boardId));
        viewCountUpdater.increaseViewCount(boardId);
        return "board/detail";
    }

    // 글쓰기 페이지
    @GetMapping("writeForm.do")
    public String getWriteForm() {

        return "board/writeForm";
    }

    //글 수정 페이지
    @GetMapping("modify.do")
    public String modifyBoard(@RequestParam int boardId, Model model) {
        model.addAttribute("board", boardService.getDetail(boardId));
        model.addAttribute("fileInfo", fileManager.getFiles(boardId));
        return "board/modify";
    }

    // 글 저장 (등록, 수정)
    @PostMapping("saveBoard.do")
    public String saveBoard(BoardSaveRequest board, @RequestParam(required = false) MultipartFile[] uploadFiles,
                            RedirectAttributes redirectAttributes, HttpSession session) throws IOException {

        // 세션에서 로그인 ID를 가져와서 등록
        String writer = ((User) session.getAttribute("user")).getUserId();

        boardService.saveBoard(board, writer);

        // 파일 등록 여부
        if(uploadFiles!= null && fileManager.checkFileListSize(uploadFiles)){
                fileManager.saveFile(board,uploadFiles);
        }

        redirectAttributes.addFlashAttribute("board", board);
        return "redirect:/board/list.do";
    }

    // 파일 다운로드
    @RequestMapping("/fileDownload.do")
    public void fileDownload(@RequestParam int fileId, HttpServletRequest request, HttpServletResponse response) throws IOException{
        fileManager.fileDownload(fileId, request, response);
    }

    // 글 삭제
    @GetMapping("delete.do")
    public String deleteBoard(@RequestParam int boardId) throws IOException {

        fileManager.clearAllFile(boardId); // 파일 삭제
        boardService.deleteBoard(boardId); // 글 삭제
        
        return "redirect:/board/list.do";
    }


    private String getLoginUserId(HttpSession session) {
        String userId = ((User) session.getAttribute("user")).getUserId();
        return userId;
    }

    private List<Board> getListWhenLoggedIn(HttpSession session, Map pagination) {
            String userId = getLoginUserId(session);
            List<Board> list = boardService.getBoardList(pagination, userId);

            return list;
    }

    private List<Board> getListWhenNotLoggedIn(Map pagination) {
        List<Board> list = boardService.getBoardList(pagination);
        return list;
    }

    private PageHandler getProcessedPageInfo(PageInfo requestedPageInfo) {
        // 등록된 글 총 개수
        int totalCount = boardService.getTotalCount();

        Integer page = requestedPageInfo.getPage();
        Integer pageSize = requestedPageInfo.getPageSize();

        PageHandler pageHandler = new PageHandler(totalCount, page, pageSize);

        return pageHandler;
    }

    private Map<String, Integer> pagination(PageHandler processedPageInfo) {
        Map<String, Integer> pagination = new HashMap();
        int page = processedPageInfo.getPage();
        int pageSize = processedPageInfo.getPageSize();
        pagination.put("offset", (page-1) * pageSize);
        pagination.put("pageSize", pageSize);

        return pagination;
    }


}
