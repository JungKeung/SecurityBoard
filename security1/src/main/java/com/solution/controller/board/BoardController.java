package com.solution.controller.board;


import com.solution.controller.ResultForm;
import com.solution.model.Board;
import com.solution.repository.BoardRepository;
import com.solution.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@Transactional
public class BoardController {

    private final BoardService boardService;
    private final BoardRepository boardRepository;

    /**
     * 게시글 작성 페이지
     * @return board/boardForm.html
     */
    @GetMapping("/board")
    public String getBoardForm(Model model){
        model.addAttribute ("boardForm", new BoardForm());
        return "board/boardForm";
    }

    /**
     * 게시글 등록
     * @param boardForm 게시글 제목 및 내용
     * @return
     */
    @PostMapping("/board")
    public String createBoardForm(@Valid BoardForm boardForm, BindingResult result, Authentication authentication){

        if (result.hasErrors()) {
            return "board/boardForm";
        }

        Board board = new Board() ;
        board.setTitle(boardForm.getTitle());
        board.setContent(boardForm.getContent());
        String email = authentication.getName();
        boardService.createBoardForm(email, board);

        return "redirect:board/list";
    }

    /**
     * 게시글 상세페이지 가져오기
     * @param id 게시글 id 값
     * @return
     */
    @GetMapping("/board/Detail")
    public String getBoardDetail(Model model, Long id, Authentication authentication){

        Board boardDetail = boardRepository.findById(id).get();

        String userEmail = authentication.getName(); // 세션에 있는 유저 이메일 가져오기
        model.addAttribute("userEmail", userEmail);
        model.addAttribute("board", boardDetail);
        return "board/boardDetail";
    }


    /**
     * 글 수정 페이지 이동
     * @param id 게시글 id 값
     * @return board/boardModify.html
     */
    @GetMapping("/board/modify/{id}")
    public String getBoardModify(@PathVariable("id") Long id, Model model) {
        // 수정이 될 특정 페이지 조회
        Board boardDetail = boardRepository.findById(id).get();
        model.addAttribute ( "board", boardDetail);
        return  "board/boardModify";
    }

    /**
     * 글 수정
     * @param id 게시글 id 값
     */
    @PostMapping("/board/modify/{id}")
    public String updateBoardDetail(@PathVariable("id") Long id, Board board) {

        // 특정 게시물에 변경될 값을 적용
        Board detailBoard = boardRepository.findById(id).get();
        detailBoard.setTitle(board.getTitle());
        detailBoard.setContent(board.getContent());

        boardRepository.save(detailBoard);

        return "redirect:/board/Detail?id="+id;
    }

    /**
     * 게시 글 삭제
     * @param id 게시글 id 값
     */
    @GetMapping("/board/delete")
    @ResponseBody
    public ResultForm deleteBoard(@RequestParam(name="id") Long id) {
        boardRepository.deleteById(id);
        return new ResultForm(0,"게시물 삭제 성공", null);
    }

    /**
     * 글 목록 가져오기
     * @param titleSearch 제목 검색어
     * @return board/boardList.html
     */
    @GetMapping("/board/list")
    public String getBoardList( Model model
            , @PageableDefault(page = 0, size =10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable
            , String titleSearch) {

        // 페이징 처리를 하여 게시글 가져오기
        Page<Board> boards = null;
        if(titleSearch == null) {
            boards = boardRepository.findAll(pageable);
        } else { // 검색된 제목이 있는 경우
            boards = boardRepository.findByTitleContaining(titleSearch, pageable);
        }

        // 페이징 처리를 위한 로직
        int nowPage = boards.getPageable().getPageNumber() + 1;
        int startPage = Math.max(nowPage -4, 1);
        int endPage = Math.min(nowPage + 5 , boards.getTotalPages());

        model.addAttribute ("boards", boards);
        model.addAttribute ( "nowPage", nowPage );
        model.addAttribute ( "startPage", startPage );
        model.addAttribute ( "endPage", endPage );

        return "board/boardList";
    }
}
