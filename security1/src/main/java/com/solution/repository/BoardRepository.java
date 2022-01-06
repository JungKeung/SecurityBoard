package com.solution.repository;

import com.solution.model.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface BoardRepository extends JpaRepository<Board, Long> {

    @Override
    List<Board> findAll();

    Page<Board> findByTitleContaining(String searchKeyword, Pageable pageable);

    Page<Board> findByContentContaining(String searchKeyword, Pageable pageable);

    Page<Board> findByUserEmailContaining(String searchKeyword, Pageable pageable);

}
