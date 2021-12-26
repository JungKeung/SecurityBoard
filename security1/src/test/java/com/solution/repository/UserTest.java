package com.solution.repository;

import com.solution.controller.user.JoinController;
import com.solution.model.User;
import com.solution.repository.UserRepository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.Optional;

import static org.junit.Assert.*;


@SpringBootTest
@RunWith(SpringRunner.class)
public class UserTest {

    @Autowired UserRepository userRepository;
    @Autowired JoinController joinController;

    @Test
    public void 닉네임중복체크() {
        Optional<Boolean> result = userRepository.existsByNickname("testNm");
        assertEquals("닉네임 중복 발생",true, result.get());
    }

    @Test
    public void 이메일중복체크() {
        Optional<Boolean> result = userRepository.existsByEmail("test@naver.com");
        assertEquals("이메일 중복 발생",true, result.get());
    }

    @Test
    public void 회원가입() {
        //given
        User user = new User();
        user.setPassword("111");
        user.setRole("ROLE_USER");
        user.setEmail("test2@naver.com");
        user.setIsAgreeMarketingTerms(true);
        user.setIsAgreePrivacyTerms(false);
        user.setNickname("testNm");

        //when
        joinController.join(user);

        //then
        assertEquals("회원가입 성공",user, userRepository.findByEmail(user.getEmail()));
    }
}

