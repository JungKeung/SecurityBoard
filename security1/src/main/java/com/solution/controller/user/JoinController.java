package com.solution.controller.user;
import com.solution.controller.ResultForm;
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

    /**
     * 회원가입 처리
     * @param user Model
     * @return redirect:board/list
     */
    @ResponseBody
    @PostMapping("/join")
    public ResultForm join(User user){

        // 유저 권한설정
        user.setRole("ROLE_USER");

        //인코딩 후 패스워드 암호화
        String userPassword = user.getPassword();
        String encPassword = bCryptPasswordEncoder.encode(userPassword);
        user.setPassword(encPassword);

        //유저 회원가입 처리
        User savedUser = userRepository.save(user);
        if (savedUser == null) {
            return new ResultForm(-9999,"회원가입 실패", null);
        }

        return new ResultForm(0,"회원가입 성공", null);
    }

    //ResponseBody는 JSON으로 변환 작업이 이루어진다.
    @ResponseBody
    @RequestMapping(value = "/existsEmail/{email}", method = RequestMethod.GET)
    public ResponseEntity<Boolean> checkEmailDuplicate(@PathVariable("email") String email){
        return ResponseEntity.ok(userService.checkDuplicatedEmail(email));
    }
}
