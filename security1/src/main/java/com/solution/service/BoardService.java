package com.solution.service;

import com.solution.model.Board;
import com.solution.model.User;
import com.solution.repository.BoardRepository;
import com.solution.repository.PagingRepository;
import com.solution.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final PagingRepository pagingRepository;
    private final UserRepository userRepository;

    //게시글 저장
    public Board created(String email, Board board){
        Optional<User> user = userRepository.findByEmail(email);
        board.setUser(user.get());
        return boardRepository.save(board);
    }

    // 게시글 수정 시 특정 Id 가져오기
    public Long update(Board board) {
        boardRepository.save ( board );
        return board.getId ();
    }

    //게시글 목록 가져오기
    public List<Board> findBoards() {
        return boardRepository.findAll();
    }

    //게시글 화면 보여주기
    public Board boardDetail (Long id) {
        return pagingRepository.findById(id).get();
    }


    //게시물 리스트 페이지 처리
    public Page<Board> boardList(Pageable pageable) {

        return pagingRepository.findAll ( pageable );
    }

    //게시물 리스트에 검색하는 기능
    public Page<Board> boardSearchList(String searchKeyword, Pageable pageable) {

        return pagingRepository.findByTitleContaining ( searchKeyword, pageable );
    }



}

