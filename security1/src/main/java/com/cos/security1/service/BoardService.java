package com.cos.security1.service;

import com.cos.security1.model.Board;
import com.cos.security1.model.User;
import com.cos.security1.repository.BoardRepository;
import com.cos.security1.repository.PagingRepository;
import com.cos.security1.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final PagingRepository pagingRepository;
    private final UserRepository userRepository;


    @Transactional
    public Integer join(Board board) {
        boardRepository.save ( board );
        return board.getId ();
    }

    public List<Board> findMembers() {
        return boardRepository.findAll();
    }

    @Transactional
    public Board boardView(Integer id) {

        return pagingRepository.findById ( id ).get ();
    }



    //게시글 리스트 처리
    public Page<Board> boardList(Pageable pageable) {

        return pagingRepository.findAll ( pageable );
    }

    public Page<Board> boardSearchList(String searchKeyword, Pageable pageable) {

        return pagingRepository.findByTitleContaining ( searchKeyword, pageable );
    }

    //특정 게시글 삭제
    public void boardDelete(Integer id) {

        pagingRepository.deleteById ( id );
    }

}

