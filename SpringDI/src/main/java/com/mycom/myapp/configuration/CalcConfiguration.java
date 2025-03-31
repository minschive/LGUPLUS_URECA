package com.mycom.myapp.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
// xml, annotation 대체
@Configuration
public class CalcConfiguration {
    // 메소드로 DI 표현
    @Bean
    public Calculator calculator() { // method 이름으로 DI
        return new Calculator();
    }
}
