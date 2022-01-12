package com.solution.repository;

import com.solution.model.Board;
import com.solution.model.User;
import com.solution.service.BoardService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

import static org.junit.Assert.assertEquals;

@WebAppConfiguration
@SpringBootTest
@RunWith(SpringRunner.class)
public class BoardTest {
    @Autowired BoardRepository boardRepository;
    @Autowired BoardService boardService;


    @Test
    public void 게시글_수정하기() {
        Board detailBoard = boardRepository.findById(1l).get();
        detailBoard.setTitle("수정될 제목");
        detailBoard.setContent("수정될 내용");
        boardRepository.save(detailBoard);

        Board detailBoard2 = boardRepository.findById(1l).get();
        assertEquals("정상적으로 수정되었습니다.",detailBoard2.getTitle(), detailBoard.getTitle());
    }
    
    @Test
    public void 특정_게시글_가져오기() {

        Board b = boardRepository.findById(1L).get();

        assertEquals("1 번 게시글이 맞다.","dd", b.getTitle());
    }

    @Test
    public void 게시글제목검색() {
        Pageable pageable= PageRequest.of(0, 10, Sort.Direction.DESC, "id");
        Page<Board> items = boardRepository.findByTitleContaining("dd", pageable);
        System.out.println(items.get().count());
    }

    @Test
    public void 게시물내용검색() {
        Pageable pageable = PageRequest.of ( 0, 10, Sort.Direction.DESC, "id" );
        Page<Board> board = boardRepository.findByContentContaining ( "dd", pageable );
        System.out.println (board.get().count());
    }

    @Test
    public void 게시물_내용_제목_검색(){
        Pageable pageable = PageRequest.of ( 0, 10, Sort.Direction.DESC, "id" );
        Page<Board> board = boardRepository.findByContentContainingIgnoreCaseOrTitleContainingIgnoreCase("오늘","ㅇㅇㅇ", pageable);
        System.out.println (board.get().count());
    }

    @Test
    public void 게시물작성자검색() {
        Pageable pageable = PageRequest.of ( 0, 10, Sort.Direction.DESC, "id" );
        Page<Board> board = boardRepository.findByUserEmailContaining( "vv323@naver.com", pageable );
        System.out.println ( board.get().count());
    }


    @Test
    public void 모든게시글가져오기() {
        List<Board> items = boardRepository.findAll();

        for(Board item : items) {
            System.out.println(item.getTitle());
        }

        // 현재 하나만 들어있음
        assertEquals("게시글 조회",1, items.size());
    }
}
