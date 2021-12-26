package com.solution.repository;

import com.solution.model.Board;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.List;

import static org.junit.Assert.assertEquals;

@SpringBootTest
@RunWith(SpringRunner.class)
public class BoardTest {
    @Autowired BoardRepository boardRepository;


    @Test
    public void 게시글제목검색() {
        Pageable pageable= PageRequest.of(0, 10, Sort.Direction.DESC, "id");

        Page<Board> items = boardRepository.findByTitleContaining("dsfadfsadsf", pageable);
        System.out.println("===========================================================");
        System.out.println(items.get().count());
        System.out.println("===========================================================");
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
