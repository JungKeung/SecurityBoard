package com.cos.security1.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity //스프링 시큐리티 필터가 스프링 필터체인에 등록이 된다
@EnableGlobalMethodSecurity(securedEnabled = true) // secured 어노테이션 활성화
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    private final AuthenticationFailureHandler customFailureHandler;


    // 해당 메서드의 리턴되는 오브젝트를 IoC로 등록해준다
    // 패스워드 암호화
    @Bean
    public BCryptPasswordEncoder encodePwd(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests()
                    .antMatchers ("/board/**").authenticated() // 인증만 되면 들어갈 수 있는 주소
                    .antMatchers ("/manager/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
                    .antMatchers ("/admin/**").access("hasRole('ROLE_ADMIN')")
                    .anyRequest().permitAll()
                .and()
                    .formLogin()
                    .loginPage("/loginForm")
                    //.usernameParameter ("email")
                    .loginProcessingUrl ("/login") //login 주소가 호출이 되면 시큐리티가 낚아채서 대신 로그인을 진행해준다. 이렇게 되면 Controller 에 login을 따로 안만들어도 된다
                    .defaultSuccessUrl ("/") //login 할시 ("/") 보내주지만 특정 페이지을 요청해서 로그인하면 특정 페이지로 이동시켜준다.
                    .failureHandler (customFailureHandler) // failureHandler 사용하여 security 에러 메세지를 사용한다.
                .and()
                    .logout () // 로그아웃 처리
                    .logoutUrl("/logout") //로그아웃 처리 URL
                    .logoutSuccessUrl ("/loginForm") //로그아웃 성공 후 이동페이지
                    .deleteCookies ("JSESSIONID", "remember-me"); //로그아웃 후 쿠키 삭제
    }
}
