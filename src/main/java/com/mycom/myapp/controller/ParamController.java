package com.mycom.myapp.controller;

import com.mycom.myapp.dto.CarDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

// client -> server 로 전송하는 parameter 처리
// servlet, jsp <= request.getParameter("name")
// Spring 은 넘어오는 파라미터를 최대한 대응하는 메소드의 파라미터에 자동화 시도
@Controller
public class ParamController {

    @GetMapping("/param1")
    public void m1(HttpServletRequest request) {
        System.out.println(request.getParameter("bookId"));
        System.out.println(request.getParameter("bookName"));
    }

    @GetMapping("/param2")
    public void m2(String bookId) { // String bookId
        System.out.println(bookId);
    }

    @GetMapping("/param3")
    public void m3(int bookId, String bookName) { // int bookId
        System.out.println(bookId);
        System.out.println(bookName);
    }

    // Optional int parameter 'book' is present but cannot be translated into a null value due to being declared as a primitive type.
    // 1. 이름으로 matching 되지 않으면 null 처리
    // 2. 메소드의 파라미터 타입이 primitive type 이면 int <-- null 불가. 위 오류 발생
    @GetMapping("/param4")
    public void m4(int book) { // IlligalstateException
        System.out.println(book);
    }

    @GetMapping("/param5")
    public void m5(Integer book) { // null
        System.out.println(book);
    }

    @GetMapping("/param6")
    public void m6(Integer bookId) { // 123
        System.out.println(bookId);
    }

    @GetMapping("/param7")
    public void m7(String bookname) { // null
        System.out.println(bookname);
    }

    // @RequestParam
    // required 속성 (true 가 default)
    @GetMapping("/param8")
    public void m8(@RequestParam String seq) { // 123, 비정상일 경우 : warn (bad request 400)
        System.out.println(seq);
    }

    @GetMapping("/param9")
    public void m9(@RequestParam(required = false) String seq) { // 123, 비정상일 경우 : null
        System.out.println(seq);
    }

    @GetMapping("/param10")
    public void m10(@RequestParam(name = "seq2") String seq) { // 123
        System.out.println(seq);
    }

    // parameter 를 Dto 로
    // int price 가 잘못된 경우 : IlligalStateException 대신 0 default 값으로 설정
    // 기본생성자 X : O <= 다른 생성자를 이용
    // 기본생성자 X, 다른 생성자 X : O <= 컴파일러가 제공하는 기본생성자 + setter 이용
    // 기본생성자 X, 다른 생성자 X, Setter X : X <= 필드값 파라미터로 초기화 X
    // 기본생성자 X, 다른 생성자 O, Setter X : X <= 다른 생성자로 초기화 X
    // price -> price2, setter, getter 는 그대로 : O
    // setPrice() -> setPrice2(), getPrice() -> getPrice2(), price 는 그대로 : X
    // Spring의 파라미터 자동화에서 Dto의 필드를 이해 <- Setter, Getter 로 처리
    @GetMapping("/car")
    public void m11(CarDto carDto){
        System.out.println(carDto);
    }

    // 복수개의 parameter 를 처리해야 하는데, 몇개가 될지, dto 가 존재 X
    // @RequestParam Map
    @GetMapping("/map")
    public void m12(@RequestParam Map<String, String> params){
        System.out.println(params.get("name"));
        System.out.println(params.get("price"));
        System.out.println(params.get("owner2"));
        System.out.println(params.get("price2"));
    }

    // Header
    @PostMapping("/header")
    public void m13(
            @RequestHeader("User-Agent") String userAgent,
            @RequestHeader("API-KEY") String apiKey
    ){
        System.out.println(userAgent);
        System.out.println(apiKey);
    }
}
