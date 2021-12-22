package com.cos.security1.config.auth;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

@Component
public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException{



        //client에 보여줄 에러메세지 (한글 및 특수문자 안됨)
        String msg = "이메일 또는 비밀번호가 잘못 입력 되었습니다.";

        if (exception instanceof BadCredentialsException){
        } else if (exception instanceof InsufficientAuthenticationException){
            msg = "email or password Error";
        }

        //영어 한글로 번역가능하게 인코딩
        msg = URLEncoder.encode(msg, "UTF-8");
        ///login?error=true&exception= 전부 작성해야 나타남
        setDefaultFailureUrl ("/login?error=true&exception="+msg);

        //이게 없으면 security에 error가 작동이 안된다.
        super.onAuthenticationFailure(request,response,exception);

    }
}
