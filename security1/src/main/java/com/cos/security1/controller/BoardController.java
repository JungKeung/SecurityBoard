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
    private final UserRepository userRepository;




    @GetMapping("/board")
    public String boardCreated(Model model){
        model.addAttribute ( "boardForm", new BoardForm());
        return "board/createdBoard";
    }

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


    @Transactional
    @GetMapping("/board/view")
    public String boardView(Board board, Model model, Long id){

        model.addAttribute ("board", boardService.boardView (id));

        return "board/boardView";
    }

    //데이터 글 가져오기
    @GetMapping("/board/list")
    public String boardList(Model model,@PageableDefault(page = 0, size =10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable
            ,String searchKeyword, User user){
        List<Board> board = boardService.findMembers ();
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

    @GetMapping("/board/modify/{id}")
    public String boardModify(@PathVariable("id") Long id, Model model){

        model.addAttribute ( "board", boardService.boardView(id));
        return  "board/boardModify";
    }

    @PostMapping("board/update/{id}")
    public String boardUpdate(@PathVariable("id") Long id, Board board) throws  Exception{

        Board boardTemp = boardService.boardView(id);
        boardTemp.setTitle (board.getTitle());
        boardTemp.setContext (board.getContext());

        boardService.join (boardTemp);

        return "redirect:/board/list";
    }


    @Transactional
    @GetMapping("/board/delete")
    public String boardDelete(Long id){
        pagingRepository.deleteById(id);
        return "redirect:/board/list";
    }




}
