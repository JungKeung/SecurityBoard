package com.solution.repository;

import com.solution.controller.user.JoinController;
import com.solution.model.User;
import com.solution.repository.UserRepository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;



@SpringBootTest
@RunWith(SpringRunner.class)
public class UserTest {

    @Autowired UserRepository userRepository;
    @Autowired JoinController joinController;
    @Test
    public void 회원가입() {
        //given
        User user = new User();
        user.setPassword("111");
        user.setRole("ROLE_USER");
        user.setEmail("test2@naver.com");
        user.setAgreeMarketingTerms(true);
        user.setAgreePrivacyTerms(false);
        user.setNickname("testNm");

        //when
        joinController.join(user);

        //then
        assertEquals("회원가입 성공",user, userRepository.findByEmail(user.getEmail()));
    }
}

