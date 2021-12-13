package com.cos.security1.controller;


import com.cos.security1.model.Board;
import com.cos.security1.model.User;
import com.cos.security1.repository.PagingRepository;
import com.cos.security1.repository.UserRepository;
import com.cos.security1.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Transactional
public class BoardController {

    private final BoardService boardService;
    private final PagingRepository pagingRepository;

    // 글 작성 페이지 가져오기
    @GetMapping("/board")
    public String boardCreated(Model model){
        model.addAttribute ( "boardForm", new BoardForm());
        return "board/createdBoard";
    }

    // 글 작성 후 저장 하기
    @PostMapping("/board")
    public String boardCreate(@Valid BoardForm form, BindingResult result, Authentication authentication){

        if (result.hasErrors()) {
            return "board/createdBoard";
        }

        Board board = new Board() ;
        board.setTitle (form.getTitle ());
        String username = authentication.getName ();
        board.setContext (form.getContext());
        boardService.save (username, board);

        return "redirect:board/list";

    }


    // 글 상세페이지 가져오기
    @GetMapping("/board/view")
    public String boardView(Board board, Model model, Long id){

        model.addAttribute ("board", boardService.boardView (id));

        return "board/boardView";
    }
    // 글 수정 화면 페이지
    @GetMapping("/board/modify/{id}")
    public String boardModify(@PathVariable("id") Long id, Model model){

        model.addAttribute ( "board", boardService.boardView(id));
        return  "board/boardModify";
    }

    // 글 수정 후 저장
    @PostMapping("board/modify/{id}") //@PathVariable url에서 각 구분자에 들어오는 값 처리
    public String boardUpdate(@PathVariable("id") Long id, Board board) throws  Exception{

        Board boardTemp = boardService.boardView(id);
        boardTemp.setTitle (board.getTitle());
        boardTemp.setContext (board.getContext());

        boardService.update (boardTemp);

        return "redirect:/board/list";
    }


    // 게시글 삭제
    @GetMapping("/board/delete")
    public String boardDelete(Long id){
        pagingRepository.deleteById(id);
        return "redirect:/board/list";
    }

    //게시글 목록 가져오기
    @GetMapping("/board/list")
    public String boardList(Model model,@PageableDefault(page = 0, size =10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable
            ,String searchKeyword, User user){
        List<Board> board = boardService.findBoards ();
        Page<Board> list = null;

        if(searchKeyword == null){
            list =boardService.boardList (pageable);
        }else {
            list =boardService.boardSearchList (searchKeyword, pageable );
        }

        int nowPage = list.getPageable ().getPageNumber () + 1;
        int startPage = Math.max(nowPage -4, 1);
        int endPage = Math.min(nowPage + 5 , list.getTotalPages ());


        model.addAttribute ("board", board);
        model.addAttribute ( "list", list);
        model.addAttribute ( "nowPage", nowPage );
        model.addAttribute ( "startPage", startPage );
        model.addAttribute ( "endPage", endPage );


        return "board/boardList";
    }






}
