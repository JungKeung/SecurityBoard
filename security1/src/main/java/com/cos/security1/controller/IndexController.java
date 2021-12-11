package com.cos.security1.controller;

import com.cos.security1.model.User;
import com.cos.security1.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Controller //View 리턴
@RequiredArgsConstructor
public class IndexController {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping({"","/"})
    public String index(){
        return "redirect:board/list";
    }

    @GetMapping("/user")
    public @ResponseBody String user(){
        return "user";
    }

    @GetMapping("/logout")
    public String logoutPage(HttpServletRequest request, HttpServletResponse response){
        new SecurityContextLogoutHandler ().logout (request,response, SecurityContextHolder.getContext().getAuthentication());
        return "loginForm";
    }


    //로그인 페이지 이동
    @GetMapping("/loginForm")
    public String loginForm(){
        return "loginForm";
    }

    //회원가입 페이지 이동동
    @PostMapping("/joinForm")
    public String joinForm(){
        return "joinForm";
    }

    //회원가입 받기
    @PostMapping("/join")
    public String join(User user){
        //Role 강제로 넣기
        user.setRole("ROLE_USER");
        //기존 패스워드를 인코딩하여 암호화
        String rawPassword = user.getPassword ();
        //인코딩을 통한 암호화
        String encPassword = bCryptPasswordEncoder.encode(rawPassword);
        //인코딩한 암호화을 DB에 넣기
        user.setPassword(encPassword);
        userRepository.save(user); //회원가입 잘됨. 이렇게 하면 비밀번호 노출되어 시큐리티로 로그인 안됨
        return "redirect:board/list";
    }

    @Secured ("ROLE_ADMIN") // 특정메서드에 권한을 걸고 싶을때 사용
    @GetMapping("/info")
    public @ResponseBody String info(){
        return "개인정보";
    }


}
