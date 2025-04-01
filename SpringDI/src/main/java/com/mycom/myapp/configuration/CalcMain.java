package com.mycom.myapp.configuration;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class CalcMain {

	public static void main(String[] args) {
		
		// Spring DI 를 통한 객체 생성
		// main() 에서 Spring Framework 의 환경
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(CalcConfiguration.class); // 설정 Java Configuration (annotation)
		Calculator calculator = (Calculator)context.getBean("calculator"); // name 기준 객체를 DI
		System.out.println(calculator.add(3, 7));
		context.close();
	}

}
