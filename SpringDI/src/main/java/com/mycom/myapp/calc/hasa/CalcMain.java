package com.mycom.myapp.calc.hasa;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CalcMain {

	public static void main(String[] args) {
		
		// Spring DI 를 통한 객체 생성
		// main() 에서 Spring Framework 의 환경
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("xml/hasa-calc-annotation.xml"); // 설정 xml 문서
		HasaCalculator hasaCalculator = (HasaCalculator)context.getBean("hasaCalculator"); // name 기준 객체를 DI
		System.out.println(hasaCalculator.add(3, 7));
		context.close();
	}

}
