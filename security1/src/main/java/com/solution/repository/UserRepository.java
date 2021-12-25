package com.solution.repository;

import com.solution.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

// JpaRepository 를 상속했으므로 해당 객체가 자동으로 빈에 등록된다.
public interface UserRepository extends JpaRepository<User, Integer> {

    //findBy규칙 -> username문법
    // select * from user where username = ?
    // 정확한 내용을 알고 싶으면 Jpa query methods 검색하면 많이 나온다
    User findByEmail(String email);

}
