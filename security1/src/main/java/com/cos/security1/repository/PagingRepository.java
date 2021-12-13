package com.cos.security1.repository;

import com.cos.security1.model.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

//게시글 페이징처리 및 검색 기능
public interface PagingRepository extends JpaRepository<Board, Long> {
    Page<Board> findByTitleContaining(String searchKeyword, Pageable pageable);
}
