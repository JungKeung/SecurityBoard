/**
 * 21.12.25
 * 이중경 (jungkeung0117@gmail.com)
 * 유저 로그인 및 로그아웃 처리
 */

package com.solution.controller.user;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequiredArgsConstructor
public class LoginController {
    // windows10_hk push test
    /**
     * 로그인 성공 시 메인화면 이동
     * @return redirect:/board/list
     */
    @GetMapping({"","/"})
    public String index(){
        return "redirect:board/list";
    }

    /**
     * 유저 로그아웃 처리
     * @param request
     * @param response
     * @return loginForm.html
     */
    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response){
        new SecurityContextLogoutHandler().logout(request, response, SecurityContextHolder.getContext().getAuthentication());
        return "loginForm";
    }

    /**
     * 유저 로그인 처리 및 로그인 실패 처리
     * 실패 시 error message return
     * @return loginForm.html
     */
    @GetMapping("/login")
    public String login(@RequestParam(value = "error", required = false)String error,
                        @RequestParam(value = "exception", required = false) String exception, Model model){
        model.addAttribute ("error",error);
        model.addAttribute ("exception", exception);
        return "loginForm";
    }
}
