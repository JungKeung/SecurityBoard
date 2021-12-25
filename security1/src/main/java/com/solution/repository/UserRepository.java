package com.solution.repository;

import com.solution.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


//CRUD 함수를 JpaRepository가 들고 있음.
// @Repository라는 어노테이션이 없어도 Ioc된다. 이유는 JpaRepository를 상속했기 때문에
public interface UserRepository extends JpaRepository<User, Integer> {

    //findBy규칙 -> username문법
    // select * from user where username = ?
    // 정확한 내용을 알고 싶으면 Jpa query methods 검색하면 많이 나온다
    User findByEmail(String email);

}