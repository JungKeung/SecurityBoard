package com.cos.security1.repository;

import com.cos.security1.model.Board;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class BoardRepository {

    private final EntityManager em;

    public Board save(Board board){
        em.persist (board);
        return board;
    }
    public Board findOne(Integer id){
        return em.find(Board.class, id);
    }

    public Board findById(Integer id) {
        return em.find(Board.class, id);
    }

    public List<Board> findAll(){
        return em.createQuery ( "select m from Board m", Board.class )
                .getResultList ();
    }
    //특정 회원의 이름으로 찾기기
    public List<Board> findByName(String name){
        return em.createQuery ( "select m from Board m where m.name = :name", Board.class)
                .setParameter ( "name",name )
                .getResultList ();

    }
}

