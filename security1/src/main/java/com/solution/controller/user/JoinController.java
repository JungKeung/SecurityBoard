package com.solution.controller.user;
import com.solution.model.User;
import com.solution.repository.UserRepository;
import com.solution.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequiredArgsConstructor
public class JoinController {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserService userService;

    /**
     * 회원가입 페이지
     * @return joinForm.html
     */
    @GetMapping("/joinForm")
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

    //ResponseBody는 JSON으로 변환 작업이 이루어진다.
    @ResponseBody
    @RequestMapping(value = "/existsEmail/{email}", method = RequestMethod.GET)
    public ResponseEntity<Boolean> checkEmailDuplicate(@PathVariable("email") String email){
        return ResponseEntity.ok(userService.checkDuplicatedEmail(email));
    }
}
