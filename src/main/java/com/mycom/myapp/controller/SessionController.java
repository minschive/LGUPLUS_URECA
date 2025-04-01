package com.mycom.myapp.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SessionController {

    @GetMapping("/session1")
    public String login(String username, String password, HttpSession session) {
        // uplus / 1234 로그인
        if("uplus".equals(username) && "1234".equals(password)) {
            // 서버공간인 session 에 username 을 추가
            session.setAttribute("username", username);
        }
        return "sessionTest1";
    }

    @GetMapping("/session2")
    public String m() {
        return "sessionTest2";
    }
}
