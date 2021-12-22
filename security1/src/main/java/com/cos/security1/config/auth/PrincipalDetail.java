package com.cos.security1.config.auth;

//시큐리티가 /login 주소 요청을 낚아채서 로그인 진행시킨다
// 로그인을 진행이 완료가 되면 시큐리티가 가지고 있는 session을 만들어준다. (Security ContextHolder)에 저장해준다
// 시큐리티에 들어가는 오브젝트가 정해져 있는데 Authentication 타입 객체여야한다.
// Authentication 안에 User 정보가 있어야 되는데 이것도 Class가 정해져 있다
// User오브젝트 타입 -> UserDetails 타입 객체여야 한다.

import com.cos.security1.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

// Security Session 영역이 있는데 Authentication이 있어야하고 이 안에는 UserDetails 타입 이여야한다.
public class PrincipalDetail implements UserDetails {

    private User user;

    public PrincipalDetail(User user){
        this.user = user;
    }

    //해당 User의 권한을 리턴하는 곳이다
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collect = new ArrayList<> ();
        collect.add ( new GrantedAuthority () {
            @Override
            public String getAuthority() {
                return user.getRole();
            }
        });
        return collect;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    public String getPassword2() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail ();
    }

    //계정 완료되었는지
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    //계정이 잠겨있는지
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    //비밀번호가 1년이상 지났는지
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    //계정이 활성화 되었는지
    @Override
    public boolean isEnabled() {

        //user.getLoginDate(); 을 가져와
        //현재 시간 - 로긴시간 -> 1년을 초과하면 return false; 라고 해주면된다.
        return true;
    }

    //false할때는 사이트에서 1년동안 회원이 로그인을 안하면 휴면계정으로 하기로 하면 false로 해야한다.
    //그러려면 model->User에 Timestamp loginDate을 지정해주어 1년이라는 시간이 지나면 잠기도록 하면된다.
}
