package com.cos.security1;

import com.cos.security1.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;


import static org.junit.Assert.assertEquals;


@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class UserTest {

    @Autowired UserService userService;

    @Test(expected = IllegalStateException.class)
    public void 회원_이메일_체크() throws Exception{
        //given

        String testEmail = "dlwndrud7@naver.com";

        //when
        //boolean existEmail = userService.checkDuplicatedEmail(testEmail);

        //then
        //assertEquals(existEmail, false); //존재하지않는 이메일
    }
}
