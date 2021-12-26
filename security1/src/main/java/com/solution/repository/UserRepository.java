package com.solution.repository;

import com.solution.model.Board;
import com.solution.model.User;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

// JpaRepository 를 상속했으므로 해당 객체가 자동으로 빈에 등록된다.
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String email);

    Optional<User> findByNickname(String nickname);

    Optional<Boolean> existsByEmail(String email);

    Optional<Boolean> existsByNickname(String nickname);
}
