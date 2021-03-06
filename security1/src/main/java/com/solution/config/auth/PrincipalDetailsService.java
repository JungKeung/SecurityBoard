package com.solution.config.auth;

import com.solution.model.User;
import com.solution.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Optional;

// 발동 조건.
// 시큐리티 설정에서 SecurityConfig -> loginProcessingUrl("/login");
// /login 요청이 오면 자동으로 UserDetailsService 타입으로 IoC되어 있는 loadUserByUsername 함수가 실행된다.
@Service
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    // loginForm.html 에서 name="username" 이라 안적고 다른 이름으로 하면
    // loadUserByUsername(String username) 함수가 작동이 안한다
    // 만약 username으로 하기싫으면 SecurityConfig 에서 .loginPage아래 .usernameParameter("원하는이름") 으로 바꿔주면 된다.
    // username -> email 로 대체함

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        // findByUsername이 없으므로 Repository에 작성해줘야한다.
        Optional<User> userEntity = userRepository.findByEmail(email);

        // user 를 찾으면 PrincipalDetail 에 return userEntity 해주고, 없으면 return null
        // 시큐리티 session(내부 Authentication(내부 UserDetail))
        if(userEntity.isPresent()){
            return new PrincipalDetail (userEntity);
        }
        return null;
    }
}
