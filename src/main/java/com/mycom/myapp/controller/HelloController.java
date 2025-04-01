package com.mycom.myapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// client 의 request 를 받고 처리
@Controller // <- 이 annotation 이 있는 클래스의 메소드들의 mapping 정보를 스프링이 수집해서 Handler Mapping 자료구조에 저장
public class HelloController {

    // get /hello
    @GetMapping("/hello")
    public String hello() {
        System.out.println("Hello....!!!!");
        return "hello"; // jsp  // /WEB-INF/jsp/hello.jsp
    }
}
// servlet 과 비교해서 쉽게, 간단하게 코드를 작성

